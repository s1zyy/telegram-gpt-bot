package selfprojects.my_telegram_gpt_bot.OpenAi.Api;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record Message(
        @JsonProperty String role,
        @JsonProperty String content) {
}
