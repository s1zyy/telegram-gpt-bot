package selfprojects.my_telegram_gpt_bot.Quotes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuotesService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${quote.token}")
    private String quote_token;

    public QuotesReply getQuote() {
        String url = "https://api.api-ninjas.com/v1/quotes";

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Api-Key", quote_token);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<QuotesReply[]> response = restTemplate.exchange(url, HttpMethod.GET, request, QuotesReply[].class);

        if(response.getBody() != null) {
            return response.getBody()[0];
        }
        return null;
    }
}
