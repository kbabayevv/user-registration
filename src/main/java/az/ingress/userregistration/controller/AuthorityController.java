package az.ingress.userregistration.controller;


import az.ingress.userregistration.model.Authority;
import az.ingress.userregistration.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authority")
@RequiredArgsConstructor
public class AuthorityController {
    private final AuthorityService authorityService;

    @PostMapping
    public Authority create(@RequestBody Authority authority) {
        return authorityService.create(authority);
    }
}
