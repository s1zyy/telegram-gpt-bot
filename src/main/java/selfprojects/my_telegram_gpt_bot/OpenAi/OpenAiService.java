package selfprojects.my_telegram_gpt_bot.OpenAi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class OpenAiService {

    @Value("${gpt.token}")
    private String token;
    private RestTemplate restTemplate;

    public String chatCompletionRequest(String message) {

        String url = "https://api.openai.com/v1/chat/completions";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        headers.add("Authorization", "Bearer " + token);

        Message userMessage = new Message("user", message);

        List<Message> messagesList = List.of(userMessage);
        ChatCompletionRequest chatCompletionRequest = new ChatCompletionRequest("gpt-4.1", messagesList);

        HttpEntity<ChatCompletionRequest> request = new HttpEntity<>(chatCompletionRequest, headers);

        ResponseEntity<ChatCompletionResponse> response = new RestTemplate().exchange(url, HttpMethod.POST, request, ChatCompletionResponse.class);

        if(response.getBody() != null){
            return response.getBody().getChoices().get(0).getMessage().getContent();
        }
        return null;

    }
}
