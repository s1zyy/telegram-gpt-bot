package selfprojects.my_telegram_gpt_bot.DataBase;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findAllByUsersEntity_ChatId(String chatId);
}
