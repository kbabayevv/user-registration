package az.ingress.userregistration;

import az.ingress.userregistration.model.Authority;
import az.ingress.userregistration.model.User;
import az.ingress.userregistration.repository.AuthorityRepository;
import az.ingress.userregistration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class UserRegistrationApplication implements CommandLineRunner {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(UserRegistrationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Authority user = Authority.builder()
//                .authority("USER")
//                .build();
//
//        Authority admin = Authority.builder()
//                .authority("ADMIN")
//                .build();
//
//        authorityRepository.save(user);
//        authorityRepository.save(admin);
//
//        User user1 = User.builder()
//                .username("user")
//                .password(passwordEncoder.encode("12345"))
//                .authorities(List.of(user))
//                .build();
//
//        User admin1 = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("12345"))
//                .authorities(List.of(admin))
//                .build();
//
//        userRepository.save(user1);
//        userRepository.save(admin1);
    }
}
