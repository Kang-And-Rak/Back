package KangRak.board.jwt;

import KangRak.board.dto.CustomUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;



public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    // JSON 요청 파싱을 위한 ObjectMapper 선언
    private final ObjectMapper objectMapper = new ObjectMapper();

    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            ServletInputStream inputStream = request.getInputStream();
            Map<String, String> requestBody = objectMapper.readValue(inputStream, Map.class);

            // JSON 데이터에서 이메일과 비밀번호 추출
            String email = requestBody.get("email");
            String password = requestBody.get("password");

            System.out.println("email = " + email);
            System.out.println("password = " + password);

            // 토큰 생성 및 인증 요청
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);

            // token 검증을 위한 절차
            return authenticationManager.authenticate(authToken);
                    // getAuthenticationManager().authenticate(authToken);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AuthenticationException("Failed to parse authentication request body") {};
        }

        // UsernamePasswordAuthenticationToken [
                // Principal=KangRak.board.dto.CustomUserDetails@2e444178,
                // Credentials=[PROTECTED],
                // Authenticated=true,
                // Details=null,
                // Granted Authorities=[KangRak.board.dto.CustomUserDetails$1@cfa6e0]
        // ]
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        // 위(LoginMethod)에서 반환한 authentication을 파라미터로 받아 인증정보로 사용

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        String email = customUserDetails.getUsername();
        Integer userId = customUserDetails.getId();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String role = auth.getAuthority();



        System.out.println("successfulAuthentication email : " + email);
        System.out.println("successfulAuthentication role : " + role);
        System.out.println("successfulAuthentication id : " + userId);

        // 60*60*10L은 token이 유지되는 시간
        String token = jwtUtil.createJwt(email,role,userId); // 60*60*10L

        response.addHeader("Authorization","Bearer " + token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        response.setStatus(401);
    }

}
