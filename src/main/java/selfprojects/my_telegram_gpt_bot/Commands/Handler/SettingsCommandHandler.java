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
    private final String settingsMessage = """
    ⚙️ Settings Menu
    
    Here you can customize your bot experience:
    • Change how the bot addresses you
    • Set the bot's communication style
    • Add your birthday for special greetings
    
    Please select an option below:
    """;

    @Override
    public BotApiMethod<?> proceedCommand(Update update) {

        String settingsMessage = getSettingsMessage();

        InlineKeyboardMarkup inlineKeyboardMarkup = getSettingsInlineKeyboardMarkup();



        return SendMessage.builder()
                .chatId(update.getMessage().getChatId())
                .text(settingsMessage)
                .replyMarkup(inlineKeyboardMarkup)
                .build();
    }

    @Override
    public TelegramCommands getSupportedCommand() {
        return TelegramCommands.SETTINGS_COMMAND;
    }


    public String getSettingsMessage(){
        return settingsMessage;
    }

    public InlineKeyboardMarkup getSettingsInlineKeyboardMarkup(){

        var button1 = InlineKeyboardButton
                .builder()
                .text("\uD83C\uDFA8 Customize Name\n")
                .callbackData("set_name")
                .build();

        var button2 = InlineKeyboardButton
                .builder()
                .text("\uD83D\uDCAD Bot Personality\n")
                .callbackData("set_tone")
                .build();

        var button3 = InlineKeyboardButton
                .builder()
                .text("\uD83C\uDF82 Birthday Reminder\n")
                .callbackData("set_birthday")
                .build();

        List<InlineKeyboardRow>  inlineKeyboardRows =List.of(
                new InlineKeyboardRow(button1),
                new InlineKeyboardRow(button2),
                new InlineKeyboardRow(button3)
        );

        return new InlineKeyboardMarkup(inlineKeyboardRows);
    }
}
