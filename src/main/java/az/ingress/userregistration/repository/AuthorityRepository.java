package az.ingress.userregistration.repository;


import az.ingress.userregistration.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

}
