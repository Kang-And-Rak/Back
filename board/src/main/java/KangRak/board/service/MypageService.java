package KangRak.board.service;

import KangRak.board.dto.CustomUserDetails;
import KangRak.board.dto.Mypage.MypageDto;
import KangRak.board.dto.PasswordUpdateRequestDto;
import KangRak.board.dto.User.UserRequestDto;
import KangRak.board.dto.User.UserResponseDto;
import KangRak.board.entity.LikePost;
import KangRak.board.entity.Post;
import KangRak.board.entity.User;
import KangRak.board.repository.CommentRepository;
import KangRak.board.repository.LikePostRepository;
import KangRak.board.repository.PostRepository;
import KangRak.board.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MypageService {

    private final LikePostRepository likePostRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ResponseEntity<MypageDto> getMypage(int userId) {
        Integer likeNum = likePostRepository.countByUserId(userId);
        Integer postNum = postRepository.countByUserId(userId);
        Integer commentNum = commentRepository.countByUserId(userId);

        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            MypageDto mypageDto = MypageDto.toMypage(user.get(),likeNum,postNum,commentNum);
            return ResponseEntity.status(201).body(mypageDto);
        }

        return ResponseEntity.status(404).body(null);

    }

    public ResponseEntity<UserResponseDto> getProfile(int userId) {
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            UserResponseDto userResponseDto = UserResponseDto.toUserResponseDto(user.get());

            return ResponseEntity.status(201).body(userResponseDto);
        }
        return ResponseEntity.status(404).body(null);
    }

    public ResponseEntity<UserResponseDto> updateProfile(UserRequestDto userRequestDto,int userId) {

        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Null 체크 후 해당 값이 있으면 업데이트
            if (userRequestDto.getName() != null) {
                user.setName(userRequestDto.getName());
            }

            if (userRequestDto.getBirthDate() != null) {
                user.setBirthDate(userRequestDto.getBirthDate());
            }

            if (userRequestDto.getStatus() != null) {
                user.setStatus(userRequestDto.getStatus());
            }

            if (userRequestDto.getCreatedAt() != null) {
                user.setCreatedAt(userRequestDto.getCreatedAt());
            }

            userRepository.save(user);

            UserResponseDto userResponseDto = UserResponseDto.toUserResponseDto(user);
            return ResponseEntity.status(201).body(userResponseDto);
        }

        return ResponseEntity.status(404).body(null);
    }

    public ResponseEntity<String> updatePassword(PasswordUpdateRequestDto passwordUpdateRequestDto, int userId) {
        // 사용자 조회
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // 확인용 비밀번호
            String password = bCryptPasswordEncoder.encode(passwordUpdateRequestDto.getCurrentPassword());

            // 현재 비밀번호가 일치하는지 확인
            if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
                return ResponseEntity.badRequest().body("현재 비밀번호가 일치하지 않습니다.");
            }

            // 새로운 비밀번호를 해싱하여 저장
            String encodedNewPassword = bCryptPasswordEncoder.encode(passwordUpdateRequestDto.getNewPassword());
            user.setPassword(encodedNewPassword);

            // 변경된 비밀번호를 데이터베이스에 저장
            userRepository.save(user);

            return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
        }

        return ResponseEntity.status(404).body(null);
    }
}
