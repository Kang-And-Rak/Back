package KangRak.board.dto;

import KangRak.board.entity.UserStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRegistDto {

    private String name;

    private String nickname;

    private String email;

    private String password;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private UserStatus status;
}
