package selfprojects.my_telegram_gpt_bot.DataBase;


import org.springframework.data.jpa.repository.JpaRepository;

public interface GptRepository extends JpaRepository<MessageEntity, Long> {
}
