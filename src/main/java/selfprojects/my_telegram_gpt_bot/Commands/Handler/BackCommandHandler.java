package selfprojects.my_telegram_gpt_bot.Commands.Handler;

import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import selfprojects.my_telegram_gpt_bot.Commands.TelegramCommandHandler;
import selfprojects.my_telegram_gpt_bot.Commands.TelegramCommands;

public class BackCommandHandler implements TelegramCommandHandler {
    @Override
    public BotApiMethod<?> proceedCommand(Update update) {
        return null;
    }

    @Override
    public TelegramCommands getSupportedCommand() {
        return TelegramCommands.BACK_COMMAND;
    }
}
