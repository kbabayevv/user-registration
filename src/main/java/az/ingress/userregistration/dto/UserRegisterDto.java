package az.ingress.userregistration.dto;

import lombok.Data;

@Data
public class UserRegisterDto {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
}
