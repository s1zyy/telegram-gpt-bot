package selfprojects.my_telegram_gpt_bot.OpenAi;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import selfprojects.my_telegram_gpt_bot.DataBase.ChatGptHistory;
import selfprojects.my_telegram_gpt_bot.DataBase.UsersRepository;
import selfprojects.my_telegram_gpt_bot.OpenAi.Api.ChatCompletionRequest;
import selfprojects.my_telegram_gpt_bot.OpenAi.Api.Message;


import java.util.List;

@Service
@AllArgsConstructor
public class GptService {
    private final OpenAiClient openAiClient;
    private final ChatGptHistory chatGptHistory;
    private final UsersRepository settingsRepository;


    public String chatCompletionRequestToUser(
            Long chatId,
            String message) {

        chatGptHistory.addMessageToHistory(chatId,message, "user");
        List<Message> history = chatGptHistory.getAllMessages(chatId);
        String tone = settingsRepository.findByChatId(chatId.toString()).get().getTone();
        history.addFirst(Message.builder()
                .role("system")
                .content(tone)
                .build());
        var request = ChatCompletionRequest.builder()
                .model("gpt-4")
                .messages(
                        history
                        )
                .build();

        var response = openAiClient.chatCompletionRequest(request);
        Message MessageResponse = response.choices().getFirst().message();
        chatGptHistory.addMessageToHistory(chatId, MessageResponse.content(),MessageResponse.role());
        return MessageResponse.content();
    }


}
