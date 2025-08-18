package selfprojects.my_telegram_gpt_bot.DataBase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
    Optional<UsersEntity> findByChatId(String chatId);
    boolean existsByChatId(String chatId);

    @Query("SELECT u FROM UsersEntity u WHERE EXTRACT(MONTH FROM u.birthday) = :month AND EXTRACT(DAY FROM u.birthday) = :day")
    List<UsersEntity> findByBirthdayMonthAndDay(@Param("month") int month, @Param("day") int day);

}
