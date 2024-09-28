package KangRak.board.controller;

import KangRak.board.dto.User.UserRegistDto;
import KangRak.board.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegistDto userRegistDto) {
        return loginService.registerUser(userRegistDto);
    }

    @PostMapping("/logout")
    public String logout() {
        // SecurityContext에서 인증 정보 클리어
        SecurityContextHolder.clearContext();

        return "로그아웃이 완료되었습니다";

    }
}
