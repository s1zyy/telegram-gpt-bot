package selfprojects.my_telegram_gpt_bot;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import selfprojects.my_telegram_gpt_bot.Commands.Handler.SettingsCommandHandler;
import selfprojects.my_telegram_gpt_bot.DataBase.SettingsHashMap;
import selfprojects.my_telegram_gpt_bot.DataBase.UsersRepository;
import selfprojects.my_telegram_gpt_bot.Keyboards.BackInlineKeyboard;


@Component
@Data
public class HandleCallBackQuery {
    private SettingsHashMap settingsHashMap;
    private UsersRepository usersRepository;
    private BackInlineKeyboard backInlineKeyboard;
    private SettingsCommandHandler settingsCommandHandler;

    public HandleCallBackQuery(SettingsHashMap settingsHashMap, UsersRepository usersRepository, BackInlineKeyboard backInlineKeyboard , SettingsCommandHandler settingsCommandHandler) {
        this.usersRepository = usersRepository;
        this.settingsHashMap = settingsHashMap;
        this.backInlineKeyboard = backInlineKeyboard;
        this.settingsCommandHandler = settingsCommandHandler;
    }


    public BotApiMethod<?> handler(CallbackQuery callbackQuery) {
        String text = callbackQuery.getData();
        Long chatId = callbackQuery.getMessage().getChat().getId();

        switch(text){
            case "set_name" -> {
                settingsHashMap.setUserState(callbackQuery.getFrom().getId(), SettingsHashMap.WAITING_FOR_NAME);
                 EditMessageText enterName = EditMessageText
                        .builder()
                        .chatId(chatId)
                        .messageId(callbackQuery.getMessage().getMessageId())
                        .text("Please enter your name:")
                        .build();
                enterName.setReplyMarkup(backInlineKeyboard.getBackInlineKeyboardMarkup());
                return enterName;
            }
            case "set_tone" -> {
                settingsHashMap.setUserState(callbackQuery.getFrom().getId(), SettingsHashMap.WAITING_FOR_TONE);
                EditMessageText enterTone = EditMessageText
                        .builder()
                        .chatId(chatId)
                        .messageId(callbackQuery.getMessage().getMessageId())
                        .text(("Please choose the tone you’d like GPT to use when talking with you.\n" +
                                "For the best experience, your chat history will be automatically cleared to adjust the tone.\n" +
                                "If you don’t want to clear the history, just go back."))
                        .build();
                enterTone.setReplyMarkup(backInlineKeyboard.getBackInlineKeyboardMarkup());
                return enterTone;
            }
            case "set_birthday" -> {
                settingsHashMap.setUserState(chatId, SettingsHashMap.WAITING_FOR_BIRTHDAY);
                EditMessageText enterBirthday = EditMessageText
                        .builder()
                        .chatId(chatId)
                        .messageId(callbackQuery.getMessage().getMessageId())
                        .text("Please enter your birthday in format: dd-mm-yyyy for example 05-05-2000: ")
                        .build();
                enterBirthday.setReplyMarkup(backInlineKeyboard.getBackInlineKeyboardMarkup());
                return enterBirthday;
            }

            case "back" -> {
                settingsHashMap.deleteUserState(callbackQuery.getFrom().getId());
                return EditMessageText
                        .builder()
                        .chatId(chatId)
                        .messageId(callbackQuery.getMessage().getMessageId())
                        .text(settingsCommandHandler.getSettingsMessage())
                        .replyMarkup(settingsCommandHandler.getSettingsInlineKeyboardMarkup())
                        .build();
            }
            default -> throw new IllegalStateException("Unexpected value: " + text);
        }

    }

}
