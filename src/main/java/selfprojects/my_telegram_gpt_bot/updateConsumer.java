package selfprojects.my_telegram_gpt_bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Voice;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import selfprojects.my_telegram_gpt_bot.Commands.TelegramCommandDispatcher;
import selfprojects.my_telegram_gpt_bot.OpenAi.GptService;
import selfprojects.my_telegram_gpt_bot.VoiceHandlers.DownloadVoice.VoiceHandlerService;
import selfprojects.my_telegram_gpt_bot.VoiceHandlers.TransferToText.TransferService;

import java.nio.file.Path;
import java.util.List;



@Component
public class updateConsumer implements LongPollingSingleThreadUpdateConsumer {

    private final TelegramClient telegramClient;
    private final GptService gptService;
    private final TelegramCommandDispatcher telegramCommandDispatcher;
    private final VoiceHandlerService voiceHandlerService;
    private final TransferService transferService;


    public updateConsumer(@Value("${bot.token}" )String botToken, GptService gptService, TelegramCommandDispatcher commandDispatcher, VoiceHandlerService voiceHandlerService, TransferService transferService) {
        this.telegramClient = new OkHttpTelegramClient(botToken);
        this.gptService = gptService;
        this.telegramCommandDispatcher = commandDispatcher;
        this.voiceHandlerService = voiceHandlerService;
        this.transferService = transferService;
    }

    @Override
    public void consume(Update update) {
        List<BotApiMethod<?>> botApiMethod = proceedCommand(update);
        botApiMethod.forEach(method -> {
            try {
                telegramClient.execute(method);

            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        });
    }


    public List<BotApiMethod<?>> proceedCommand(Update update){
        if(telegramCommandDispatcher.isCommand(update)){
            return List.of(telegramCommandDispatcher.proceedCommand(update));
        }
        else if(update.hasMessage() && update.getMessage().hasText()){
            Long chatId = extractChatId(update);
            String message = update.getMessage().getText();
            String reply = gptService.chatCompletionRequestToUser(chatId, message);
            return buildMessage(chatId, reply);
        }
        else if(update.hasMessage() && update.getMessage().hasVoice()){
            Long chatId = extractChatId(update);
            try {
                telegramClient.execute(buildMessage(chatId,"This is very long process, please wait...").getFirst());
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
            Voice voice = update.getMessage().getVoice();
            Path voiceMessage = voiceHandlerService.downloadVoice(voice);
            String transferredText = transferService.transferToText(voiceMessage);
            String replyFromGpt = gptService.chatCompletionRequestToUser(chatId, transferredText);
            return buildMessage(chatId, replyFromGpt);
        }
        return List.of();
    }


    private void handleCallbackQuery(CallbackQuery callbackQuery) {
        String text = callbackQuery.getData();
        Long chatId = callbackQuery.getMessage().getChat().getId();

        switch(text){
            case "gpt" -> buildMessage(chatId, "Ask gpt your question:");
            case "project" -> buildMessage(chatId, "Nothing here yet!");
            case "author" -> buildMessage(chatId, "Nothing here yet!");
            default -> buildMessage(chatId, "Nothing here yet!");
        }

    }

    private void sendReplyMenu(Long chatId) {
        SendMessage sendMessage = SendMessage
                .builder()
                .chatId(chatId)
                .text("This is your reply keyboard!")
                .build();

        List<KeyboardRow> keyboardRow = List.of(
                new KeyboardRow("/gpt", "/quote")
        );

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(keyboardRow);

        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        try {
            telegramClient.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }


    private void sendMainMenu(Long chatId) {

        SendMessage sendMessage = SendMessage
                .builder()
                .chatId(chatId)
                .text("This is your main menu!")
                .build();

        var button1 = InlineKeyboardButton.builder()
                .text("Ask GPT a question")
                .callbackData("gpt")
                .build();
        var button2 = InlineKeyboardButton.builder()
                .text("About this project")
                .callbackData("project")
                .build();
        var button3 = InlineKeyboardButton.builder()
                .text("About author")
                .callbackData("author")
                .build();

        List<InlineKeyboardRow> inlineKeyboardRows = List.of(
                new InlineKeyboardRow(button1),
                new InlineKeyboardRow(button2),
                new InlineKeyboardRow(button3)
        );

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(inlineKeyboardRows);
        sendMessage.setReplyMarkup(markup);
        try {
            telegramClient.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    public List<BotApiMethod<?>> buildMessage(Long chatId, String message){

        SendMessage sendMessage = SendMessage
                .builder()
                .chatId(chatId)
                .text(message)
                .build();
        return List.of(sendMessage);
    }


    public Long extractChatId(Update update){
        if(update.hasMessage()){return update.getMessage().getChatId();}
        else if(update.hasCallbackQuery()){return update.getCallbackQuery().getMessage().getChatId();}
        else if(update.hasEditedMessage()){return update.getEditedMessage().getChatId();}
        else if(update.hasChannelPost()){return update.getChannelPost().getChatId();}
        else{return null;}
    }

}
