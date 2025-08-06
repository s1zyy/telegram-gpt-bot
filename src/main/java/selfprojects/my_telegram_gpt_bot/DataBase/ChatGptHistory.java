package selfprojects.my_telegram_gpt_bot.DataBase;

import org.springframework.stereotype.Component;
import selfprojects.my_telegram_gpt_bot.OpenAi.Api.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class ChatGptHistory {

    private final MessageRepository messageRepository;
    private final UsersRepository usersRepository;

    public ChatGptHistory(MessageRepository gptRepository, UsersRepository usersRepository) {
        this.messageRepository = gptRepository;
        this.usersRepository = usersRepository;
    }

    public void addMessageToHistory(Long id, String message, String role){
        UsersEntity usersEntity = usersRepository.findByChatId(id.toString()).orElseThrow();

        MessageEntity messageEntity = MessageEntity
                .builder()
                .message(message)
                .role(role)
                .usersEntity(usersEntity)
                .build();
        messageRepository.save(messageEntity);
    }

    public List<Message> getAllMessages(Long id) {
        List<Message> mesagesList = new ArrayList<>();
        List<MessageEntity> messageEntities = messageRepository.findAllByUsersEntity_ChatId(id.toString());

        return messageEntities
                .stream()
                .map(entity -> new Message(entity.getRole(), entity.getMessage()))
                .collect(Collectors.toList());
    }

    public void deleteAllMessages(Long id){
        List<MessageEntity> messageEntities = messageRepository.findAllByUsersEntity_ChatId(id.toString());
        messageRepository.deleteAll(messageEntities);
    }

    public void deleteUser(Long id){
        Optional<UsersEntity> user =  usersRepository.findByChatId(id.toString());
        if(user.isPresent()){
            usersRepository.delete(user.get());
        }else{
            throw new IllegalStateException("User not found");
        }
    }


}