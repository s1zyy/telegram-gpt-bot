package selfprojects.my_telegram_gpt_bot.DataBase;

import org.springframework.stereotype.Component;
import selfprojects.my_telegram_gpt_bot.OpenAi.Api.Message;


import java.util.ArrayList;
import java.util.List;


@Component
public class ChatGptHistory {

    private final GptRepository gptRepository;

    public ChatGptHistory(GptRepository gptRepository) {
        this.gptRepository = gptRepository;
    }

    public void addMessageToHistory(Long id, String message, String role){
        MessageEntity messageEntity = MessageEntity
                .builder()
                .chatId(id)
                .message(message)
                .role(role)
                .build();
        gptRepository.save(messageEntity);
        //return getAllMessages(id);
        
    }

    public List<Message> getAllMessages(Long id) {
        List<Message> mesagesList = new ArrayList<>();
        List<MessageEntity> messageEntities = gptRepository.findAll();
        for(int i = 0; i < messageEntities.size(); i++) {
            MessageEntity entity = messageEntities.get(i);
            if (entity.getChatId().equals(id)) {
                mesagesList.add(new Message(entity.getRole(), entity.getMessage()));
            }
        }
        return mesagesList;
    }

public void deleteAllMessages(Long id){
        List<MessageEntity> messageEntities = gptRepository.findAll()
                .stream()
                .filter(messageEntity -> messageEntity.getChatId().equals(id))
                .toList();
        gptRepository.deleteAll(messageEntities);
}
    




}
