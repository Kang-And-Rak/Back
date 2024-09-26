package KangRak.board;

import KangRak.board.dto.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

    // 현재 로그인된 사용자 정보 가져오기
    public static Integer getCurrentUserId() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getId(); // UserDetails에서 사용자 ID 가져오기
    }
}
