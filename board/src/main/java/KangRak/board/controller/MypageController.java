package KangRak.board.controller;
import KangRak.board.SecurityUtil;
import KangRak.board.dto.CustomUserDetails;
import KangRak.board.dto.Mypage.MypageDto;
import KangRak.board.dto.PasswordUpdateRequestDto;
import KangRak.board.dto.User.UserRequestDto;
import KangRak.board.dto.User.UserResponseDto;
import KangRak.board.entity.LikePost;
import KangRak.board.entity.User;
import KangRak.board.repository.LikePostRepository;
import KangRak.board.service.MypageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static KangRak.board.SecurityUtil.getCurrentUserId;

@RestController
@RequestMapping("/mypage")
@AllArgsConstructor
public class MypageController {

    private final MypageService mypageService;


    @GetMapping("")
    public ResponseEntity<MypageDto> mypage() {

        // 현재 로그인된 사용자 정보 가져오기
        Integer userId = getCurrentUserId();

        return mypageService.getMypage(userId);
    }

    @GetMapping("/info")
    public ResponseEntity<UserResponseDto> getProfile() {

        // 현재 로그인된 사용자 정보 가져오기
        Integer userId = getCurrentUserId();

        return mypageService.getProfile(userId);

    }

    @PutMapping("/info")
    public ResponseEntity<UserResponseDto> updateProfile(@RequestBody UserRequestDto userRequestDto) {

        // 현재 로그인된 사용자 정보 가져오기
        Integer userId = getCurrentUserId();

        return mypageService.updateProfile(userRequestDto, userId);
    }

    @PutMapping("/password")
    public ResponseEntity<String> updatePassword(@RequestBody PasswordUpdateRequestDto passwordUpdateRequestDto) {

        // 현재 로그인된 사용자 ID 가져오기
            Integer userId = SecurityUtil.getCurrentUserId();

            return mypageService.updatePassword(passwordUpdateRequestDto,userId);
    }

}
