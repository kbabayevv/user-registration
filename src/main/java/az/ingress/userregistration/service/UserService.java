package az.ingress.userregistration.service;

import az.ingress.userregistration.dto.request.UserRequest;
import az.ingress.userregistration.dto.request.UserRequestResetPassword;
import az.ingress.userregistration.dto.response.UserResponse;
import az.ingress.userregistration.model.User;
import az.ingress.userregistration.repository.UserRepository;
import az.ingress.userregistration.util.SecurityUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static az.ingress.userregistration.config.UserMapper.INSTANCE;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;




    public UserResponse userRegister(UserRequest request) {
        Optional<User> byUsernameExists = userRepository.findByUsername(request.getUsername());
        if (byUsernameExists.isPresent()) {
            throw new EntityNotFoundException("User already exists. Please, Try another username !");
        } else {
            User user = INSTANCE.mapUserRequestToUserEntity(request);
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            User saved = userRepository.save(user);
            return INSTANCE.mapEntityToUserResponse(saved);
        }
    }


    public String userLogin() {
        return "You are logged in successfully !";
    }


    public String userResetPassword(UserRequestResetPassword request) {
        User user = userRepository.findByUsername(SecurityUtil.getLoggedUserId()).orElseThrow();
        if (request.getPassword().equals(request.getConfirmPassword())) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            userRepository.save(user);
        } else throw new RuntimeException("Your confirm password doesn't match");
        return "Your password is changed successfully !";
    }

}
