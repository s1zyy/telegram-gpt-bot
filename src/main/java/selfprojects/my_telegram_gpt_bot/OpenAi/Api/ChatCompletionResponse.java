package selfprojects.my_telegram_gpt_bot.OpenAi.Api;


import java.util.List;


public record ChatCompletionResponse(List<Choice> choices) {
}
