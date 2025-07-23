package selfprojects.my_telegram_gpt_bot.Commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public interface TelegramCommandHandler {
    BotApiMethod<?> proceedCommand(Update update);

    TelegramCommands getSupportedCommand();

}
