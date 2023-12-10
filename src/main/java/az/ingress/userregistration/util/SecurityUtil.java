package az.ingress.userregistration.util;

import az.ingress.userregistration.config.security.JwtCredentials;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUtil {
    public static String getLoggedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object credentials = authentication.getCredentials();
        JwtCredentials jwtCredentials= (JwtCredentials) credentials;
        return jwtCredentials.getSub();
    }

}