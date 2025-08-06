package selfprojects.my_telegram_gpt_bot.Settings;

import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import selfprojects.my_telegram_gpt_bot.DataBase.UsersEntity;
import selfprojects.my_telegram_gpt_bot.DataBase.SettingsHashMap;
import selfprojects.my_telegram_gpt_bot.DataBase.UsersRepository;

import java.util.Optional;

@Service
@Transactional
public class SettingsService {
    private final SettingsHashMap settingsHashMap;
    private final UsersRepository settingsRepository;

    public SettingsService(SettingsHashMap settingsHashMap, UsersRepository settingsRepository) {
        this.settingsHashMap = settingsHashMap;
        this.settingsRepository = settingsRepository;
    }



    public void innitSettings(Update update,Long chatId) {

        if(settingsRepository.existsByChatId(chatId.toString())) return;

        String name = update.getMessage().getFrom().getFirstName();
        UsersEntity usersEntity = UsersEntity
                .builder()
                .chatId(chatId.toString())
                .userName(name)
                .tone("You are a helpfull assistant!")
                .build();
        try{
            settingsRepository.save(usersEntity);
        }
        catch (DataIntegrityViolationException e){
            System.out.println("A problem occurred");
        }
    }

    public boolean handler(String text, Long chatId) {
        if (settingsHashMap.isWaitingForName(chatId)) {
            Optional<UsersEntity> user_settings = settingsRepository.findByChatId(chatId.toString());
            if (user_settings.isPresent()) {
                user_settings.get().setUserName(text);
            }
            settingsHashMap.deleteUserState(chatId);
            return false;
        }
        else if (settingsHashMap.isWaitingForTone(chatId)) {
            Optional<UsersEntity> user_settings = settingsRepository.findByChatId(chatId.toString());
            if (user_settings.isPresent()) {
                user_settings.get().setTone(text);
            }
            settingsHashMap.deleteUserState(chatId);
            return false;

        }

        return true;

    }
}
