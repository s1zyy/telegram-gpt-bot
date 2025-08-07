package selfprojects.my_telegram_gpt_bot;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import selfprojects.my_telegram_gpt_bot.DataBase.SettingsHashMap;
import selfprojects.my_telegram_gpt_bot.DataBase.UsersRepository;


@Component
@Data
public class HandleCallBackQuery {
    private SettingsHashMap settingsHashMap;
    private UsersRepository usersRepository;

    public HandleCallBackQuery(SettingsHashMap settingsHashMap, UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.settingsHashMap = settingsHashMap;
    }


    public BotApiMethod<?> handler(CallbackQuery callbackQuery) {
        String text = callbackQuery.getData();
        Long chatId = callbackQuery.getMessage().getChat().getId();

        switch(text){
            case "set_name" -> {
                settingsHashMap.setUserState(callbackQuery.getFrom().getId(), SettingsHashMap.WAITING_FOR_NAME);
                return SendMessage
                        .builder()
                        .chatId(chatId)
                        .text("Please enter your name:")
                        .build();
            }
            case "set_tone" -> {
                settingsHashMap.setUserState(callbackQuery.getFrom().getId(), SettingsHashMap.WAITING_FOR_TONE);
                return SendMessage
                        .builder()
                        .chatId(chatId)
                        .text("Please enter tone you would like GPT to talk with you:")
                        .build();
            }
            case "set_birthday" -> {
                settingsHashMap.setUserState(chatId, SettingsHashMap.WAITING_FOR_BIRTHDAY);
                return SendMessage
                        .builder()
                        .chatId(chatId)
                        .text("Please enter your birthday in format: dd-mm-yyyy for example 05-05-2000: ")
                        .build();

            }
            default -> throw new IllegalStateException("Unexpected value: " + text);
        }

    }

}
