package KangRak.board.jwt;

import KangRak.board.dto.CustomUserDetails;
import KangRak.board.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// 권한 확인 - 인가 과정
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // header에서 Authorization 찾음
        String authorization = request.getHeader("Authorization");
        System.out.println("authorization : " + authorization);

           if(authorization == null || !authorization.startsWith("Bearer ")) {
               System.out.println("token null");
               filterChain.doFilter(request,response);
               return;
           }

           String token = authorization.split(" ")[1];
            System.out.println(token);

//           if(jwtUtil.isExpired(token)) {
//               System.out.println("token expired");
//               filterChain.doFilter(request,response);
//               return;
//           }

           String email = jwtUtil.getEmail(token);
           String role = jwtUtil.getRole(token);
           Integer id = jwtUtil.getId(token);

        System.out.println("email in token : " + email);
        System.out.println("role in token : " + role);
        System.out.println("id in token : " + id);

           User user = new User();
           user.setEmail(email);
           user.setPassword("temppassword");
           user.setRole(role);
           user.setId(id);

        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        System.out.println(customUserDetails.getAuthorities());
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails,null,customUserDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request,response);

    }
}
