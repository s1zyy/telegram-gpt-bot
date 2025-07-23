package selfprojects.my_telegram_gpt_bot.Commands.Handler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import selfprojects.my_telegram_gpt_bot.Commands.TelegramCommandHandler;
import selfprojects.my_telegram_gpt_bot.Commands.TelegramCommands;
import selfprojects.my_telegram_gpt_bot.NumberFact.NumberService;

@Component
public class NumberFactCommandHandler implements TelegramCommandHandler {

    private final NumberService numberService;
    public NumberFactCommandHandler(NumberService numberService) {
        this.numberService = numberService;
    }

    @Override
    public BotApiMethod<?> proceedCommand(Update update) {
        if(update.getMessage().getText().split(" ").length < 2){
            return SendMessage.builder()
                    .chatId(update.getMessage().getChatId())
                    .text("Please enter one number after command /number")
                    .build();
        }
        int number = Integer.parseInt(update.getMessage().getText().split(" ")[1]);
        return SendMessage.builder()
                .chatId(update.getMessage().getChatId())
                .text(numberService.number(number))
                .build();
    }

    @Override
    public TelegramCommands getSupportedCommand() {
        return TelegramCommands.NUMBERFACT_COMMAND;
    }
}
