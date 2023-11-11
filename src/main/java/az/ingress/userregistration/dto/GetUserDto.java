package az.ingress.userregistration.dto;

import lombok.Data;

@Data
public class GetUserDto {
    private Integer id;
    private String username;
    private String email;
}
