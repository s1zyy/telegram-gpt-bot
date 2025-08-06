package selfprojects.my_telegram_gpt_bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MyTelegramGptBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyTelegramGptBotApplication.class, args);
    }

}
