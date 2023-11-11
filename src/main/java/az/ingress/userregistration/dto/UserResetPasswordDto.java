package az.ingress.userregistration.dto;

import lombok.Data;

@Data
public class UserResetPasswordDto {
    private String email;
    private String password;
    private String confirmPassword;
}
