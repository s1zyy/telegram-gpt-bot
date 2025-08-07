package selfprojects.my_telegram_gpt_bot.DataBase;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Table(name = "users")
@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;


    @Column(name = "chat_id", unique = true)
    private String chatId;

    @Column(name = "tone")
    private String tone;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "usersEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<MessageEntity> messageEntityList = new ArrayList<>();

    @Column(name = "birthday")
    private LocalDate birthday;


    @Transient
    private int getAge(){
        return Period.between(birthday, LocalDate.now()).getYears();
    }

}
