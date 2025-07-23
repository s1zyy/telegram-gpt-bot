package selfprojects.my_telegram_gpt_bot.Commands;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TelegramCommandDispatcher {

    private final List<TelegramCommandHandler> telegramCommandHandlerList;


    public BotApiMethod<?> proceedCommand(Update update) {
        if(!isCommand(update)) {
            throw new IllegalStateException("Not a command passed");
        }
        String text = update.getMessage().getText();

        Optional<TelegramCommandHandler> suitedHandler = telegramCommandHandlerList.stream()
                .filter(some -> text.startsWith(some.getSupportedCommand().getCommandValue()))
                .findAny();
        if(suitedHandler.isEmpty()){
            return SendMessage.builder()
                    .chatId(update.getMessage().getChatId())
                    .text("Command not found")
                    .build();
        }
        return suitedHandler.orElseThrow().proceedCommand(update);
    }



    public boolean isCommand(Update update) {
        return update.hasMessage()
                && update.getMessage().hasText()
                && update.getMessage().getText().startsWith("/");
    }

}
