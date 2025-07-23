package selfprojects.my_telegram_gpt_bot.Commands.Handler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import selfprojects.my_telegram_gpt_bot.Commands.TelegramCommandHandler;
import selfprojects.my_telegram_gpt_bot.Commands.TelegramCommands;

@Component
public class StartCommandHandler implements TelegramCommandHandler {

    private final String HELLO_USER = """
                Hello %s
                Using this bot your can speak with GPT
                Each message is saved for logic
                To clear message history type /clear
                To get a random quote type /quote
                To get a random number fact type /number (your_number)
            """;
    @Override
    public BotApiMethod<?> proceedCommand(Update update) {
        return SendMessage.builder()
                .chatId(update.getMessage().getChatId())
                .text(HELLO_USER.formatted(
                        update.getMessage().getFrom().getFirstName()))
                .build();
    }

    @Override
    public TelegramCommands getSupportedCommand() {
        return TelegramCommands.START_COMMAND;
    }
}
