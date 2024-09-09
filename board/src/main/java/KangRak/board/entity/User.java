package KangRak.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String nickname;

    private String email;

    private LocalDate birthDate;

    private String userId;

    private String password;

    @Enumerated(EnumType.STRING)
    private userStatus status;

    private Integer point;

    @Enumerated(EnumType.STRING)
    private rankStatus rank;

    @CreatedDate
    @Column(updatable = false) // 수정불가
    private LocalDateTime createdAt;

    @OneToMany
    @JoinColumn
    private List<Post> posts;

}

enum userStatus {
    GRADUATE,     // 졸업생
    STUDENT,   // 재학생
    LEAVE     // 휴학생
}

enum rankStatus {
    BRONZE,
    SILVER,
    GOLD,
    PLATINUM,
    AMERALD,
    DIAMOND,
    MASTER,
    GRANDMASTER,
    CHALLENGER
}
