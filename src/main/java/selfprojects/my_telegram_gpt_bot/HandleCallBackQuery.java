package selfprojects.my_telegram_gpt_bot;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import selfprojects.my_telegram_gpt_bot.DataBase.SettingsHashMap;


@Component
@Data
public class HandleCallBackQuery {
    private SettingsHashMap settingsHashMap;

    public HandleCallBackQuery(SettingsHashMap settingsHashMap) {
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
            default -> throw new IllegalStateException("Unexpected value: " + text);
        }

    }

}
