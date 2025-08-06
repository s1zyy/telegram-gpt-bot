package selfprojects.my_telegram_gpt_bot.DataBase;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class SettingsHashMap {
    public static final String WAITING_FOR_NAME = "WAITING_FOR_NAME";
    public static final String WAITING_FOR_TONE = "WAITING_FOR_TONE";
    private final HashMap<Long, String> settingsHashMap = new HashMap<>();

    public void setUserState(Long userId, String state){
        settingsHashMap.put(userId, state);
    }

    public String getUserState(Long userId){
        return settingsHashMap.get(userId);
    }

    public void deleteUserState(Long userId){
        settingsHashMap.remove(userId);
    }

    public boolean isWaitingForName(Long userId){
        return WAITING_FOR_NAME.equals(getUserState(userId));
    }
    public boolean isWaitingForTone(Long userId){
        return WAITING_FOR_TONE.equals(getUserState(userId));
    }


}
