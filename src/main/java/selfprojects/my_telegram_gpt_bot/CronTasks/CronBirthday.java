package selfprojects.my_telegram_gpt_bot.CronTasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import selfprojects.my_telegram_gpt_bot.DataBase.UsersEntity;
import selfprojects.my_telegram_gpt_bot.DataBase.UsersRepository;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.ZoneId;
import java.util.List;


@Component
public class CronBirthday {

    private final TelegramClient telegramClient;
    private final UsersRepository usersRepository;

    public CronBirthday(TelegramClient telegramClient, UsersRepository usersRepository) {
        this.telegramClient = telegramClient;
        this.usersRepository = usersRepository;
    }

    @Scheduled(cron = "0 0 9 * * ?")
    public void happyBirthday(){

        LocalDate today = LocalDate.now(ZoneId.of("Europe/Dublin"));
        System.out.println(today);
        MonthDay monthDay = MonthDay.from(today);

        List<UsersEntity> birthdayHuman = usersRepository.findAll();

        List<UsersEntity> filteredHumans = birthdayHuman.stream()
                .filter(user -> MonthDay.from(user.getBirthday()).equals(monthDay))
                .toList();
        filteredHumans
                .forEach(usersEntity -> {

                    SendMessage message = SendMessage
                            .builder()
                            .chatId(usersEntity.getChatId())
                            .text("Happy Birthday " + usersEntity.getUserName() + "!")
                            .build();
                    try {
                        telegramClient.execute(message);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                   }
                });
    }
}
