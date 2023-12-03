package az.ingress.userregistration.service;

import az.ingress.userregistration.model.Authority;
import az.ingress.userregistration.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    public Authority create(Authority authority) {
        return authorityRepository.save(authority);
    }
}
