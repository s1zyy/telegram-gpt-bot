package selfprojects.my_telegram_gpt_bot.VoiceHandlers.Records;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResultResponse(
        @JsonProperty("file_id")
        String fileId,
        @JsonProperty("file_path")
        String filePath
        ) {
}
