package selfprojects.my_telegram_gpt_bot.VoiceHandlers.DownloadVoice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import selfprojects.my_telegram_gpt_bot.VoiceHandlers.Records.FileDownloadResponse;

@Component
public class GetPathService {
    @Value("${bot.token}")
    private String bot_token;
    RestTemplate restTemplate = new RestTemplate();

    public String getFilePath(String fileId){
        String url = "https://api.telegram.org/bot%s/getFile?file_id=%s".formatted(bot_token, fileId);

        FileDownloadResponse filePath = restTemplate.getForObject(url, FileDownloadResponse.class);

        if(filePath != null){
            return filePath.result().filePath();
        }
        else{
            throw new RuntimeException("Failed to get file path");
        }
    }
}
