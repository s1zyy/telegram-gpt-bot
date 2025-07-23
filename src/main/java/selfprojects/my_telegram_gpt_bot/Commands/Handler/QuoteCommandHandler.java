package selfprojects.my_telegram_gpt_bot.Commands.Handler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import selfprojects.my_telegram_gpt_bot.Commands.TelegramCommandHandler;
import selfprojects.my_telegram_gpt_bot.Commands.TelegramCommands;
import selfprojects.my_telegram_gpt_bot.Quotes.QuotesService;

@Component
public class QuoteCommandHandler implements TelegramCommandHandler {
    private final QuotesService quotesService;
    public QuoteCommandHandler(QuotesService quotesService) {
        this.quotesService = quotesService;
    }
    @Override
    public BotApiMethod<?> proceedCommand(Update update) {
        String quote = quotesService.getQuote().quote();
        String author = quotesService.getQuote().author();
        String answer = "\""+ quote+"\" by "+author;
        return SendMessage.builder()
                .chatId(update.getMessage().getChatId())
                .text(answer)
                .build();
    }

    @Override
    public TelegramCommands getSupportedCommand() {
        return TelegramCommands.QUOTES_COMMAND;
    }
}
