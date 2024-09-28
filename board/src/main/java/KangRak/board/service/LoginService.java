package KangRak.board.service;

import KangRak.board.dto.User.UserRegistDto;
import KangRak.board.entity.User;
import KangRak.board.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ResponseEntity<String> registerUser(UserRegistDto userRegistDto) {

        // DB에 동일한 email이 존재하는지를 확인
        if(userRepository.existsByEmail(userRegistDto.getEmail())){
            return new ResponseEntity<>("Already Exist Email", HttpStatus.BAD_REQUEST);
        }

        String bcryptPassword = bCryptPasswordEncoder.encode(userRegistDto.getPassword());
        userRegistDto.setPassword(bcryptPassword);

        userRepository.save(User.toUser(userRegistDto));

        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }
}
