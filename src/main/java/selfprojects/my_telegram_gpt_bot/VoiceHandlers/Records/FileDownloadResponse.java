package selfprojects.my_telegram_gpt_bot.VoiceHandlers.Records;

import com.fasterxml.jackson.annotation.JsonProperty;


public record FileDownloadResponse(
        @JsonProperty("ok")
        boolean ok,
        @JsonProperty("result")
        ResultResponse result
){
}
