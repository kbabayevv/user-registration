package az.ingress.userregistration.controller;


import az.ingress.userregistration.dto.request.UserRequest;
import az.ingress.userregistration.dto.request.UserRequestResetPassword;
import az.ingress.userregistration.dto.response.UserResponse;
import az.ingress.userregistration.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/register")
    public UserResponse userRegister(@Valid @RequestBody UserRequest request) {
        return userService.userRegister(request);
    }

    @GetMapping("/login")
    public String userLogin() {
        return userService.userLogin();
    }

    @PutMapping("/reset")
    public String userResetPassword(@Valid @RequestBody UserRequestResetPassword requestRestPassword) {
        return userService.userResetPassword(requestRestPassword);
    }

}
