package az.ingress.userregistration.service;

import az.ingress.userregistration.dto.GetUserDto;
import az.ingress.userregistration.dto.UserLoginDto;
import az.ingress.userregistration.dto.UserRegisterDto;
import az.ingress.userregistration.dto.UserResetPasswordDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    GetUserDto getUserById(Integer id);

    List<GetUserDto> getAllUsers();

    void userRegister(UserRegisterDto userRegisterDto);

    void userLogin(UserLoginDto userLoginDto);

    void userResetPassword(UserResetPasswordDto userResetPasswordDto);

    void deleteById(Integer id);

    void deleteAllUsers();

}
