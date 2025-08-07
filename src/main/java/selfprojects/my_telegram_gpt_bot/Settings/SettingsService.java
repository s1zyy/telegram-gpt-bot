package selfprojects.my_telegram_gpt_bot.Settings;

import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import selfprojects.my_telegram_gpt_bot.DataBase.UsersEntity;
import selfprojects.my_telegram_gpt_bot.DataBase.SettingsHashMap;
import selfprojects.my_telegram_gpt_bot.DataBase.UsersRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@Transactional
public class SettingsService {
    private final SettingsHashMap settingsHashMap;
    private final UsersRepository settingsRepository;
    private final String defaultTone;

    public SettingsService(SettingsHashMap settingsHashMap, UsersRepository settingsRepository) {
        this.settingsHashMap = settingsHashMap;
        this.settingsRepository = settingsRepository;
        this.defaultTone = "You are a helpful assistant!";
    }



    public void innitSettings(Update update,Long chatId) {

        if(settingsRepository.existsByChatId(chatId.toString())) return;

        String name = update.getMessage().getFrom().getFirstName();
        UsersEntity usersEntity = UsersEntity
                .builder()
                .chatId(chatId.toString())
                .userName(name)
                .tone(defaultTone)
                .build();
        try{
            settingsRepository.save(usersEntity);
        }
        catch (DataIntegrityViolationException e){
            System.out.println("A problem occurred");
        }
    }




    public String handler(String text, Long chatId) {
        if (settingsHashMap.isWaitingForName(chatId)) {
            Optional<UsersEntity> user_settings = settingsRepository.findByChatId(chatId.toString());
            if (user_settings.isPresent()) {
                user_settings.get().setUserName(text);
            }
            settingsHashMap.deleteUserState(chatId);
            return "Your name is successfully updated!";
        }
        else if (settingsHashMap.isWaitingForTone(chatId)) {
            Optional<UsersEntity> user_settings = settingsRepository.findByChatId(chatId.toString());
            if (user_settings.isPresent()) {
                user_settings.get().setTone(text);
            }
            settingsHashMap.deleteUserState(chatId);
            return "Your tone is successfully updated!";
        }
        else if (settingsHashMap.isWaitingForBirthday(chatId)) {
            Optional<UsersEntity> user_settings = settingsRepository.findByChatId(chatId.toString());
            if (user_settings.isPresent()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate birthday;
                try{
                    birthday = LocalDate.parse(text,formatter);
                }
                catch (Exception e){
                    return "Please enter your birthday correctly! The format is: dd-mm-yyyy (Example 05-05-2000)";
                }
                user_settings.get().setBirthday(birthday);
            }
            settingsHashMap.deleteUserState(chatId);
            return "Your birthday is successfully updated!";
        }

        return null;

    }
}
