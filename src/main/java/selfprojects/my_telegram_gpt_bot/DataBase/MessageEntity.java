package selfprojects.my_telegram_gpt_bot.DataBase;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "gptmessagesTest")
@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "message")
    private String message;

    @Column(name = "role")
    private String role;
}
