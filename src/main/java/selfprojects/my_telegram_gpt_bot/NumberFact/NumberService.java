package selfprojects.my_telegram_gpt_bot.NumberFact;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NumberService {

    private final RestTemplate restTemplate =  new RestTemplate();
    public String number(int number) {
        String url = "http://numbersapi.com/" + number;


        ResponseEntity<String> reply = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, String.class);
        if(reply.getBody() != null) {
            return reply.getBody();
        }
        else  {
            return null;
        }
    }
}
