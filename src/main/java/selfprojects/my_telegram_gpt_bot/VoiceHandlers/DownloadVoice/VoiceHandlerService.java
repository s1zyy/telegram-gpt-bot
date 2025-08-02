package selfprojects.my_telegram_gpt_bot.VoiceHandlers.DownloadVoice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Voice;

import java.io.IOException;
import java.nio.file.Path;


@Service
@AllArgsConstructor
public class VoiceHandlerService {

    private GetPathService getFilePath;
    private DownloadService downloadFile;

    public Path downloadVoice(Voice voice) {
        String filePath = getFilePath.getFilePath(voice.getFileId());

        Path audioFile;
        try {
            audioFile = downloadFile.downloadFileToTemp(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return audioFile;
    }
}
