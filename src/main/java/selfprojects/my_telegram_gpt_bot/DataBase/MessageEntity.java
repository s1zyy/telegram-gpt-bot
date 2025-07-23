package selfprojects.my_telegram_gpt_bot.DataBase;

import jakarta.persistence.*;
import lombok.*;


@Table(name = "gptmessages")
@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chatid")
    private Long chatId;

    @Column(name = "message")
    private String message;

    @Column(name = "role")
    private String role;
}
