package selfprojects.my_telegram_gpt_bot.VoiceHandlers.TransferToText;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Path;

@Service
public class TransferService {
    @Value("${bot.token}")
    private String botToken;

    @Value("${gpt.token}")
    private String gptToken;

    private final RestTemplate restTemplate = new RestTemplate();

    public String transferToText(Path voiceMessage) {
        String url = "https://api.openai.com/v1/audio/transcriptions";
        FileSystemResource file = new FileSystemResource(voiceMessage);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file);
        body.add("model", "whisper-1");
        body.add("response_format", "text");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + gptToken);
        headers.add(HttpHeaders.CONTENT_TYPE, "multipart/form-data");

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        return response.getBody();
    }

}
