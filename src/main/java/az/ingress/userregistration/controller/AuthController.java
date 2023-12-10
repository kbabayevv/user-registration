package az.ingress.userregistration.controller;


import az.ingress.userregistration.config.security.JwtService;
import az.ingress.userregistration.dto.AuthenticationDTO;
import az.ingress.userregistration.dto.JWTResponseDTO;
import az.ingress.userregistration.dto.request.UserRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/authentication")
@AllArgsConstructor
public class AuthController {
    private final JwtService jwtService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping
    public ResponseEntity<JWTResponseDTO> authenticate(@Valid @RequestBody AuthenticationDTO dto) {
        authenticationManagerBuilder.getObject().authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );

        String token = jwtService.createToken(dto.getUsername());
        JWTResponseDTO jwtResponseDTO = new JWTResponseDTO();
        jwtResponseDTO.setAccessToken(token);
        return ResponseEntity.ok(jwtResponseDTO);
    }
}