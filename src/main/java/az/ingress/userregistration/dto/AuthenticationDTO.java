package az.ingress.userregistration.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
public class AuthenticationDTO {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}