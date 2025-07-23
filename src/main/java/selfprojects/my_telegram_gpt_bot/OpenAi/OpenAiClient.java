package selfprojects.my_telegram_gpt_bot.OpenAi;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import selfprojects.my_telegram_gpt_bot.OpenAi.Api.ChatCompletionRequest;
import selfprojects.my_telegram_gpt_bot.OpenAi.Api.ChatCompletionResponse;


@Component
@RequiredArgsConstructor
public class OpenAiClient {

    @Value("${gpt.token}")
    private String token;


    private final RestTemplate restTemplate;

    @Value("${gpt.url}")
    private String gpt_url;

    public ChatCompletionResponse chatCompletionRequest(ChatCompletionRequest chatCompletionRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        headers.add("Authorization", "Bearer " + token);

        HttpEntity<ChatCompletionRequest> request = new HttpEntity<>(chatCompletionRequest, headers);

        ResponseEntity<ChatCompletionResponse> response = restTemplate.exchange(gpt_url, HttpMethod.POST, request, ChatCompletionResponse.class);

        if(response.getBody() != null){
            return response.getBody();
        }
        return null;

    }
}
