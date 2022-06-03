package marcat.managerpage.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcat.security.JwtAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("myPage")
@RequiredArgsConstructor
public class MappingControllerController {

    private final JwtAuthenticationProvider provider;

    @GetMapping
    public String myPageMain(HttpServletRequest request) {
        try {
            String token = provider.resolveToken(request);
            Authentication authentication = provider.getAuthentication(token);
            Collection<? extends GrantedAuthority> roleObject = authentication.getAuthorities();
            String role = roleObject.toString();

            if (role.contains("ROLE_USER")) {
                return "redirect:/user";
            }else if (role.contains("ROLE_ADMIN")) {
                return "redirect:/admin";
            }
        }catch (IllegalArgumentException e){
            return "redirect:/";
        }

        return "redirect:/";
    }
}

