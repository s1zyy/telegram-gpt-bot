package selfprojects.my_telegram_gpt_bot.OpenAi;

import java.util.List;


public class ChatCompletionRequest {
    private String model;
    private List<Message> messages;


    public ChatCompletionRequest(String model, List<Message> messages) {
        this.model = model;
        this.messages = messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public String getModel() {
        return model;
    }
}
