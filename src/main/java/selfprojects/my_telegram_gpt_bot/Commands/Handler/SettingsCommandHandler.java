package selfprojects.my_telegram_gpt_bot.Commands.Handler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;
import selfprojects.my_telegram_gpt_bot.Commands.TelegramCommandHandler;
import selfprojects.my_telegram_gpt_bot.Commands.TelegramCommands;

import java.util.List;

@Component
public class SettingsCommandHandler implements TelegramCommandHandler {
    @Override
    public BotApiMethod<?> proceedCommand(Update update) {

        SendMessage sendMessage = SendMessage
                .builder()
                .chatId(update.getMessage().getChatId())
                .text("Here is your setting menu!")
                .build();

        var button1 = InlineKeyboardButton
                .builder()
                .text("Set name")
                .callbackData("set_name")
                .build();

        var button2 = InlineKeyboardButton
                .builder()
                .text("Set GPT's tone")
                .callbackData("set_tone")
                .build();

        List<InlineKeyboardRow>  inlineKeyboardRows =List.of(
                new InlineKeyboardRow(button1),
                new InlineKeyboardRow(button2)
        );

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(inlineKeyboardRows);

        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        return sendMessage;
    }

    @Override
    public TelegramCommands getSupportedCommand() {
        return TelegramCommands.SETTINGS_COMMAND;
    }
}
