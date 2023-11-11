package az.ingress.userregistration.service;

import az.ingress.userregistration.dto.GetUserDto;
import az.ingress.userregistration.dto.UserLoginDto;
import az.ingress.userregistration.dto.UserRegisterDto;
import az.ingress.userregistration.dto.UserResetPasswordDto;
import az.ingress.userregistration.exception.NotNullException;
import az.ingress.userregistration.exception.PasswordMatchException;
import az.ingress.userregistration.exception.UserAlreadyExistsException;
import az.ingress.userregistration.exception.UserNotExistsException;
import az.ingress.userregistration.model.User;
import az.ingress.userregistration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public GetUserDto getUserById(Integer id) {
        User user = userRepository.findById(id).get();
        return modelMapper.map(user, GetUserDto.class);
    }

    @Override
    public List<GetUserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> modelMapper.map(user, GetUserDto.class)).collect(Collectors.toList());
    }

    @Override
    public void userRegister(UserRegisterDto userRegisterDto) {
        Optional<User> email = userRepository.findByEmail(userRegisterDto.getEmail());

        if (email.isPresent()) {
            throw new UserAlreadyExistsException("User already exists!");
        }

        if (userRegisterDto.getUsername().isEmpty() || userRegisterDto.getEmail().isEmpty() ||
                userRegisterDto.getPassword().isEmpty() || userRegisterDto.getConfirmPassword().isEmpty()) {
            throw new NotNullException("Enter something!");
        }

        if (!Objects.equals(userRegisterDto.getPassword(), userRegisterDto.getConfirmPassword())) {
            throw new PasswordMatchException("Password Match Exception");
        }

        User user = modelMapper.map(userRegisterDto, User.class);
        userRepository.save(user);
    }

    @Override
    public void userLogin(UserLoginDto userLoginDto) {

        Optional<User> checkUserExistsInDatabase = userRepository.findByUsernameAndPassword(userLoginDto.getUsername(), userLoginDto.getPassword());
        if (checkUserExistsInDatabase.isPresent()) {
            System.out.println("Successfully Complete");
        } else {
            throw new UserNotExistsException("Username or Password Not True");
        }
    }

    @Override
    public void userResetPassword(UserResetPasswordDto userResetPasswordDto) throws NotNullException {
        Optional<User> userExistsInDatabase = userRepository.findByEmail(userResetPasswordDto.getEmail());

        if (userExistsInDatabase.isPresent()) {
            if (Objects.equals(userResetPasswordDto.getPassword(), userResetPasswordDto.getConfirmPassword())) {
                userExistsInDatabase.map(user -> {
                    user.setUsername(user.getUsername());
                    user.setEmail(user.getEmail());
                    user.setPassword(userResetPasswordDto.getPassword());
                    user.setConfirmPassword(userResetPasswordDto.getConfirmPassword());
                    return userRepository.save(user);
                });
            } else {
                throw new PasswordMatchException("Password Match Exception!");
            }
        } else {
            throw new UserNotExistsException("User Not Exists!");
        }
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

}
