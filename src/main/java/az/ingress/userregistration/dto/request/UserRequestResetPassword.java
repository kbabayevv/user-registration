package az.ingress.userregistration.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserRequestResetPassword {

    @NotEmpty
    @Size(min = 6, max = 100)
    String password;

    @NotEmpty
    @Size(min = 6, max = 100)
    String confirmPassword;
}
