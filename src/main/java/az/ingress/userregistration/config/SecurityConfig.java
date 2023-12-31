package az.ingress.userregistration.config;

import az.ingress.userregistration.config.security.AuthFilterConfigurerAdapter;
import az.ingress.userregistration.config.security.TokenAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final TokenAuthService tokenAuthService;
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().exceptionHandling().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeHttpRequests()
                .requestMatchers("/authority").permitAll()
                .requestMatchers("/authentication").permitAll()
                .requestMatchers("/user/register").permitAll()
                .requestMatchers("/user/reset").hasAnyAuthority("USER", "ADMIN", "MANAGER")
                .requestMatchers("/user/login").hasAnyAuthority("USER", "ADMIN", "MANAGER")
                .anyRequest().authenticated();

        http.apply(new AuthFilterConfigurerAdapter(tokenAuthService));
        return http.build();
    }


}
