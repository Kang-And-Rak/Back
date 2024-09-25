package KangRak.board.controller;
import KangRak.board.service.MypageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mypage")
@AllArgsConstructor
public class MypageController {

    private final MypageService mypageService;

    // GET /mypage 요청 처리
    @GetMapping
    public ResponseEntity<String> getMypage(Authentication authentication) {

        // 인증 객체에서 사용자의 이메일을 가져와 서비스에 전달
        System.out.println("getMypage");
        System.out.println(authentication);
//        String userEmail = authentication.getName();
//        String mypageData = mypageService

        return ResponseEntity.ok("good");
    }
}
