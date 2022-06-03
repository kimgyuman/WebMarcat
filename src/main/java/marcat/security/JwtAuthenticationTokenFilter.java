package marcat.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 헤더에서 JWT 토큰 가져오기
        String token = jwtAuthenticationProvider.resolveToken(request);

        // 유효한 토큰인지 확인
        if(token != null && jwtAuthenticationProvider.validateToken(token)){
            Authentication authentication = jwtAuthenticationProvider.getAuthentication(token); // JWT 토큰에서 인증 정보 조회
            SecurityContextHolder.getContext().setAuthentication(authentication); // securityContextHolder에 인증 정보 저장
        }
        filterChain.doFilter(request, response);
    }
}
