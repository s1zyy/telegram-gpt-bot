package selfprojects.my_telegram_gpt_bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import selfprojects.my_telegram_gpt_bot.OpenAi.OpenAiService;

import java.util.List;



@Component
public class updateConsumer implements LongPollingSingleThreadUpdateConsumer {

    private final TelegramClient telegramClient;

    private final OpenAiService openAiService;

    public updateConsumer(@Value("${bot.token}" )String botToken, OpenAiService openAiService){
        this.telegramClient = new OkHttpTelegramClient(botToken);
        this.openAiService = openAiService;
    }

//    @Bean// need to better understand bean working    еще код не запускается если использовать бин, пишет что он идет в телеграм
    //  бот что бы создать объект OkHttpTelegramClient а там он требует создать объект updateConsumer и получается замкнутый круг
    //попробовать мейби решить с папой
//    public OkHttpTelegramClient telegramClient(@Value("${bot.token}" )String botToken){
//        return new OkHttpTelegramClient(botToken);
//    }

    @Override
    public void consume(Update update) {

        String reply;

        var chatId = extractChatId(update);

        if(update.hasMessage() && update.getMessage().hasText()){
            String message = update.getMessage().getText();
            if(message.equals("/start")){
                sendMainMenu(chatId);
            }
            else{
                String checkReply = openAiService.chatCompletionRequest(update.getMessage().getText());
                if(checkReply != null){
                    reply = checkReply;
                }
                else{
                    reply = "Didnt get message from GPT :(";
                }
                sendMessage(chatId,reply);
            }

        }
        else if(update.hasCallbackQuery()){
            handleCallbackQuery(update.getCallbackQuery());
        }
    }

    private void handleCallbackQuery(CallbackQuery callbackQuery) {
        String text = callbackQuery.getData();
        Long chatId = callbackQuery.getMessage().getChat().getId();

        switch(text){
            case "gpt" -> sendMessage(chatId, "Ask gpt your question:");
            case "project" -> sendMessage(chatId, "Nothing here yet!");
            case "author" -> sendMessage(chatId, "Nothing here yet!");
            default -> sendMessage(chatId, "Nothing here yet!");
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
    public void sendMessage(Long chatId, String message){

        SendMessage sendMessage = SendMessage
                .builder()
                .chatId(chatId)
                .text(message)
                .build();
        try {
            telegramClient.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }


    public Long extractChatId(Update update){
        if(update.hasMessage()){return update.getMessage().getChatId();}
        else if(update.hasCallbackQuery()){return update.getCallbackQuery().getMessage().getChatId();}
        else if(update.hasEditedMessage()){return update.getEditedMessage().getChatId();}
        else if(update.hasChannelPost()){return update.getChannelPost().getChatId();}
        else{return null;}
    }
}
