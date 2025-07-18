package selfprojects.my_telegram_gpt_bot;

import org.springframework.stereotype.Component;

@Component
public class OpenAiService {
    public void askGpt(Long chatId) {
        System.out.printf("Entering askGpt(%d): ", chatId);
    }
}
