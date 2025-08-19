package selfprojects.my_telegram_gpt_bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Voice;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import selfprojects.my_telegram_gpt_bot.Commands.TelegramCommandDispatcher;
import selfprojects.my_telegram_gpt_bot.OpenAi.GptService;
import selfprojects.my_telegram_gpt_bot.Settings.SettingsService;
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
    private final HandleCallBackQuery handleCallBackQuery;
    private final SettingsService settingsService;


    public updateConsumer(TelegramClient telegramClient, GptService gptService, TelegramCommandDispatcher commandDispatcher, VoiceHandlerService voiceHandlerService, TransferService transferService, HandleCallBackQuery handleCallBackQuery, SettingsService settingsService) {
        this.telegramClient = telegramClient;
        this.gptService = gptService;
        this.telegramCommandDispatcher = commandDispatcher;
        this.voiceHandlerService = voiceHandlerService;
        this.transferService = transferService;
        this.handleCallBackQuery = handleCallBackQuery;
        this.settingsService = settingsService;
    }

    @Override
    public void consume(Update update) {
        List<Object> botApiMethod = proceedCommand(update);
        botApiMethod.forEach(method -> {

            if(method instanceof SendSticker){
                try {
                    telegramClient.execute((SendSticker) method);

                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
            else if(method instanceof SendMessage){
                try {
                    telegramClient.execute((SendMessage)method);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }

        });
    }


    public List<Object> proceedCommand(Update update){
        Long chatId = extractChatId(update);
        if(chatId != null){
            settingsService.innitSettings(update,chatId);
        }
        if(telegramCommandDispatcher.isCommand(update)){
            return List.of(telegramCommandDispatcher.proceedCommand(update));
        }

        else if(update.hasCallbackQuery()){
            return List.of(handleCallBackQuery.handler(update.getCallbackQuery()));
        }

        else if(update.hasMessage()) {

            if (update.getMessage().hasVoice()) {
                try {
                    telegramClient.execute((BotApiMethod<?>)buildMessage(chatId, "This is very long process, please wait...").getFirst());
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                Voice voice = update.getMessage().getVoice();
                Path voiceMessage = voiceHandlerService.downloadVoice(voice);
                String transferredText = transferService.transferToText(voiceMessage);
                String replyFromGpt = gptService.chatCompletionRequestToUser(chatId, transferredText);
                return buildMessage(chatId, replyFromGpt);

            } else if (update.getMessage().hasText()) {
                String text = update.getMessage().getText();
                String settings = settingsService.handler(text, chatId);
                if(settings != null){
                    return buildMessage(chatId, settings);
                }

                else{
                    String message = update.getMessage().getText();
                    String reply = gptService.chatCompletionRequestToUser(chatId, message);
                    return buildMessage(chatId, reply);
                }

            }
            else if(update.getMessage().hasSticker()){
                String stickerId = update.getMessage().getSticker().getFileId();

                if (chatId == null) throw new AssertionError();
                SendSticker sendSticker = SendSticker.builder()
                        .sticker(new InputFile(stickerId))
                        .chatId(chatId)
                        .build();

                var message = SendMessage.builder()
                        .chatId(chatId)
                        .text("Sticker function is not developed!")
                        .build();
                return List.of(message, sendSticker);
            }

        }
        return List.of();
    }

    public List<Object> buildMessage(Long chatId, String message){

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
