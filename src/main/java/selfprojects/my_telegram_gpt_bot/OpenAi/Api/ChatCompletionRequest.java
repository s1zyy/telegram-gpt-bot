package selfprojects.my_telegram_gpt_bot.OpenAi.Api;

import lombok.Builder;

import java.util.List;

@Builder
public record ChatCompletionRequest(String model, List<Message> messages) {
}
