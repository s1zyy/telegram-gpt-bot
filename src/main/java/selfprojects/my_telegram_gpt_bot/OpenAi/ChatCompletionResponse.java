package selfprojects.my_telegram_gpt_bot.OpenAi;

import lombok.Data;

import java.util.List;



@Data
public class ChatCompletionResponse {
    private List<Choice> choices;
}
