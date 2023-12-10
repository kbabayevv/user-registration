package az.ingress.userregistration.config.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenAuthService {

    private static final String BEARER = "Bearer ";

    private static final String AUTHORITY_CLAIM = "authorities";
    private final JwtService jwtService;

    private final ModelMapper mapper;


    public Optional<Authentication> getAuthentication(HttpServletRequest req) {
        return Optional.ofNullable(req.getHeader("Authorization"))
                .filter(this::isBearerAuth)
                .flatMap(this::getAuthenticationBearer);
    }

    private boolean isBearerAuth(String header) {
        return header.toLowerCase().startsWith(BEARER.toLowerCase());
    }

    private Optional<Authentication> getAuthenticationBearer(String header) {
        var token = header.substring(BEARER.length()).trim();
        var claims = jwtService.parseToken(token);
        log.info("The claims parsed {}", claims);
        if (claims.getExpiration().before(new Date())) {
            return Optional.empty();
        }
        return Optional.of(getAuthenticationBearer(claims));
    }

    private Authentication getAuthenticationBearer(Claims claims) {
        List<?> roles = claims.get(AUTHORITY_CLAIM, List.class);
        List<GrantedAuthority> authorityList = roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.toString()))
                .collect(Collectors.toList());
        JwtCredentials credentials = mapper.map(claims, JwtCredentials.class);

        User user = new User(credentials.getSub(), "", authorityList);

        return new UsernamePasswordAuthenticationToken(user, credentials, authorityList);
    }

}
