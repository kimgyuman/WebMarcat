package marcat.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginPageFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;

        // 이미 로그인 된 사용자가 login과 add르 시작되는 주소로 접근하면 home으로 redirect함
        String chemin = servletRequest.getRequestURI();
        if (isAuthenticated() && chemin.startsWith("/add")) {
            redirectmethod(servletRequest, servletResponse);
        } else if (isAuthenticated() && chemin.startsWith("/login")) {
            redirectmethod(servletRequest, servletResponse);
        } else {
            chain.doFilter(servletRequest, servletResponse);
        }
    }

    // isAuthenticated method
    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    // redirect method
    public void redirectmethod(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        String encodedRedirectURL = servletResponse.encodeRedirectURL(
                servletRequest.getContextPath() + "/");

        servletResponse.setStatus(HttpStatus.TEMPORARY_REDIRECT.value());
        servletResponse.setHeader("Location", encodedRedirectURL);
    }
}