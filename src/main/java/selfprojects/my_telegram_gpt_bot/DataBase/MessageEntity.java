package selfprojects.my_telegram_gpt_bot.DataBase;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "gptmessages_test")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id", referencedColumnName = "chat_id")
    private UsersEntity usersEntity;


    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @Column(name = "role")
    private String role;

}
