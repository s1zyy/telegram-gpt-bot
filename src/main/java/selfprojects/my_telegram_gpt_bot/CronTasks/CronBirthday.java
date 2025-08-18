package selfprojects.my_telegram_gpt_bot.CronTasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import selfprojects.my_telegram_gpt_bot.DataBase.UsersEntity;
import selfprojects.my_telegram_gpt_bot.DataBase.UsersRepository;
import selfprojects.my_telegram_gpt_bot.Redis.RedisCache;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.ZoneId;
import java.util.List;


@Component
public class CronBirthday {

    private final TelegramClient telegramClient;
    private final UsersRepository usersRepository;
    private final RedisCache redisCache;

    public CronBirthday(TelegramClient telegramClient, UsersRepository usersRepository, RedisCache redisCache) {
        this.telegramClient = telegramClient;
        this.usersRepository = usersRepository;
        this.redisCache = redisCache;
    }

    @Scheduled(cron = "0 0 9 * * ?")
    public void happyBirthday(){

        LocalDate today = LocalDate.now(ZoneId.of("Europe/Dublin"));

        MonthDay monthDay = MonthDay.from(today);

        int day = monthDay.getDayOfMonth();
        int month = monthDay.getMonthValue();

        List<UsersEntity> birthdayHuman = usersRepository.findByBirthdayMonthAndDay(month, day);

        birthdayHuman
                .forEach(usersEntity -> {

                    boolean checkGreeting = redisCache.hasRedis(usersEntity.getChatId());
                    if(checkGreeting){return;}

                    redisCache.setGreeted(usersEntity.getChatId());

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
