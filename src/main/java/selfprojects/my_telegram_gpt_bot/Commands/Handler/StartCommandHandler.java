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
                🤖 Hello, %s! 👋
            
                Welcome to your personal GPT-powered assistant.
            
                📝 Available commands:
                • 💬 Simply send a message to chat with me
                • 🧹 /clear - Clear chat history
                • 💡 /quote - Get inspiring quote
                • 🔢 /number - Fun number facts
                • ⚙️ /settings - Personalize bot
            
                ✨ Pro tip: Our conversation history is saved to provide better context!
            
                Let's start chatting! 🚀
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
