package selfprojects.my_telegram_gpt_bot.DataBase;

import selfprojects.my_telegram_gpt_bot.OpenAi.Api.Message;

import java.util.List;

public record ChatHistory(
        List<Message> messageList
) {
}
