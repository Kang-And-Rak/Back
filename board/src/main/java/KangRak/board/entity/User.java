package KangRak.board.entity;

import KangRak.board.dto.UserRegistDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String role;

    private String name;

    private String nickname;

    @Column(unique = true) // 중복 방지를 위함.
    private String email;

    private LocalDate birthDate;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private Integer point;

    @Enumerated(EnumType.STRING)
    @Column(name = "`rank`")
    private RankStatus rank;

    @CreatedDate
    @Column(updatable = false) // 수정불가
    private LocalDateTime createdAt;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Post> posts = new ArrayList<>();

    public static User toUser(UserRegistDto userRegistDto) {
        User user = new User();

        user.setRole("admin");
        user.setName(userRegistDto.getName());
        user.setNickname(userRegistDto.getNickname());
        user.setEmail(userRegistDto.getEmail());
        user.setPassword(userRegistDto.getPassword());
        user.setStatus(userRegistDto.getStatus());
        user.setPoint(0);
        user.setRank(RankStatus.BRONZE);
        user.setCreatedAt(LocalDateTime.now());
        user.setPosts(new ArrayList<>());

        return user;
    }
}




