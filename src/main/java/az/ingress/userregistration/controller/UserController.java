package az.ingress.userregistration.controller;

import az.ingress.userregistration.dto.GetUserDto;
import az.ingress.userregistration.dto.UserLoginDto;
import az.ingress.userregistration.dto.UserRegisterDto;
import az.ingress.userregistration.dto.UserResetPasswordDto;
import az.ingress.userregistration.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public GetUserDto getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/all")
    public List<GetUserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public void userRegister(@RequestBody UserRegisterDto userRegisterDto) {
        userService.userRegister(userRegisterDto);
    }

    @PostMapping("/login")
    public void userLogin(@RequestBody UserLoginDto userLoginDto) {
        userService.userLogin(userLoginDto);
    }

    @PutMapping("/reset")
    public void userResetPassword(@RequestBody UserResetPasswordDto userResetPasswordDto) {
        userService.userResetPassword(userResetPasswordDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
    }

    @DeleteMapping("/all")
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }
}
