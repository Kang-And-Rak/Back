package KangRak.board.dto.User;

import KangRak.board.entity.User;
import KangRak.board.entity.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {

    private String name;            // 사용자 이름
    private LocalDate birthDate; // 사용자 생년월일
    private UserStatus status;          // 사용자 상태 (enum 타입)
    private LocalDateTime createdAt; // 계정 생성일

    // 필요에 따라 추가적인 메서드나 로직을 작성할 수 있습니다.
    public static UserRequestDto toUserRequestDto(User user) {
        UserRequestDto userRequestDto = new UserRequestDto();

        userRequestDto.setName(user.getName());
        userRequestDto.setBirthDate(user.getBirthDate());
        userRequestDto.setStatus(user.getStatus());
        userRequestDto.setCreatedAt(user.getCreatedAt());

        return userRequestDto;
    }
}
