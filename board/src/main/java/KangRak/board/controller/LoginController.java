package KangRak.board.controller;

import KangRak.board.dto.UserLoginDto;
import KangRak.board.dto.UserRegistDto;
import KangRak.board.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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

}
