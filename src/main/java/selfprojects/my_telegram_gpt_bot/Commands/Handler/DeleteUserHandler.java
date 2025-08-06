package selfprojects.my_telegram_gpt_bot.Commands.Handler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import selfprojects.my_telegram_gpt_bot.Commands.TelegramCommandHandler;
import selfprojects.my_telegram_gpt_bot.Commands.TelegramCommands;
import selfprojects.my_telegram_gpt_bot.DataBase.ChatGptHistory;

@Component
public class DeleteUserHandler implements TelegramCommandHandler {
    private final ChatGptHistory chatGptHistory;

    public DeleteUserHandler(ChatGptHistory chatGptHistory) {
        this.chatGptHistory = chatGptHistory;
    }

    @Override
    public BotApiMethod<?> proceedCommand(Update update) {
        chatGptHistory.deleteUser(update.getMessage().getFrom().getId());
        return SendMessage
                .builder()
                .chatId(update.getMessage().getChatId())
                .text("User successfully deleted!")
                .build();
    }

    @Override
    public TelegramCommands getSupportedCommand() {
        return TelegramCommands.DELETEUSER_COMMAND;
    }
}

