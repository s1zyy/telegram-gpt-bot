package selfprojects.my_telegram_gpt_bot.DataBase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
    Optional<UsersEntity> findByChatId(String chatId);
    boolean existsByChatId(String chatId);
}
