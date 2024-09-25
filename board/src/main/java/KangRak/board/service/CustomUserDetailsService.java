package KangRak.board.service;

import KangRak.board.dto.CustomUserDetails;
import KangRak.board.entity.User;
import KangRak.board.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


// Spring Security에서 유저의 정보를 가져오는 인터페이스로 가져온 유저의 정보를 UserDetails로 리턴
@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User userData = userRepository.findByEmail(email);
        System.out.println("userData : " + userData);

        if(userData != null) {
            return new CustomUserDetails(userData);
        }
        return null;
    }
}
