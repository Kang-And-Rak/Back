package KangRak.board;

import KangRak.board.jwt.JWTFilter;
import KangRak.board.jwt.JWTUtil;
import KangRak.board.jwt.LoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity //모든 요청 URL이 스프링 시큐리티의 제어를 받도록 만드는 애너테이션
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration ) throws Exception {
        return configuration.getAuthenticationManager();
    }

    //모든 요청 URL에 이 클래스가 필터로 적용되어 URL별로 특별한 설정을 할 수 있게 됨
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // csrf 공격을 방어할 필요가 없으므로
        http
                .csrf((auth) -> auth.disable());

        http
                .httpBasic((auth) -> auth.disable());

        http
                .formLogin((auth) -> auth.disable());

        // 익명 사용자 필터 비활성화
        http.anonymous(anonymous -> anonymous.disable());

        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers( "/","/register","/login","/error").permitAll() // 모든 사용자에게 접근 허용
                        .requestMatchers("/admin" , "/post").hasRole("ADMIN")
                        .anyRequest().authenticated());

        // 만든 필터를 UsernamePasswordAuthenticationFilter 대신에 사용하는 것
        http.
                addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration),jwtUtil) , UsernamePasswordAuthenticationFilter.class);

        http
                .addFilterBefore(new JWTFilter(jwtUtil),UsernamePasswordAuthenticationFilter.class);



        return http.build();

    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
