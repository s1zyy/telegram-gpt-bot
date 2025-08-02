package selfprojects.my_telegram_gpt_bot.VoiceHandlers.DownloadVoice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class DownloadService {
    @Value("${bot.token}")
    private String bot_token;
    RestTemplate restTemplate;

    public DownloadService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Path downloadFileToTemp(String filePath) throws IOException {
        String url = "https://api.telegram.org/file/bot%s/%s".formatted(bot_token, filePath);
        ResponseEntity<byte[]> reply = restTemplate.getForEntity(url, byte[].class);
        if(reply.getBody() != null && reply.getStatusCode().is2xxSuccessful()){
            Path tempPath = Files.createTempFile("telegram-bot-voice-", ".ogg");
            Files.write(tempPath, reply.getBody());
            return tempPath;
        }
        else{
            throw new IOException();
        }
    }

}
