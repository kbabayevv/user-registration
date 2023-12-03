package az.ingress.userregistration.dto.request;

import az.ingress.userregistration.model.Authority;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserRequest {

    @NotEmpty
    String username;


    @NotEmpty
    @Size(min = 6, max = 100)
    String password;

    @NotNull
    List<Authority> authorities;
}
