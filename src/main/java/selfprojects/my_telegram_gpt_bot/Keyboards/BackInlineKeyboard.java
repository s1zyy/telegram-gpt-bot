package selfprojects.my_telegram_gpt_bot.Keyboards;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

import java.util.List;

@Component
public class BackInlineKeyboard {

    public InlineKeyboardMarkup getBackInlineKeyboardMarkup(){
        var backButton = InlineKeyboardButton.builder()
                .text("⬅️ Back to Menu\n")
                .callbackData("back")
                .build();

        List<InlineKeyboardRow> inlineKeyboardRows = List.of(new InlineKeyboardRow(backButton));

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(inlineKeyboardRows);

        return inlineKeyboardMarkup;
    }
}
