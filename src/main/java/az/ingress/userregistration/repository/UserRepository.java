package az.ingress.userregistration.repository;

import az.ingress.userregistration.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameAndPassword(String username, String password);
}