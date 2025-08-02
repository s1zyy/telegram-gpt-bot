package selfprojects.my_telegram_gpt_bot.DataBase;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Table
@Entity
@Builder
@Data
@RequiredArgsConstructor
public class SettingsEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "chat_id")
    private String chatId;

    @Column(name = "tone")
    private String tone;

}
