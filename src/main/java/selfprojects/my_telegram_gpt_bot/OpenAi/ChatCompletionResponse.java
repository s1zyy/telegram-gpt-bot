package selfprojects.my_telegram_gpt_bot.OpenAi;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;



@Data
public class ChatCompletionResponse {
    private List<Choice> choices;
}
