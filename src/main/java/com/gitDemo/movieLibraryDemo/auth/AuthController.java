package com.gitDemo.movieLibraryDemo.auth;

import com.gitDemo.movieLibraryDemo.auth.jwt.JwtTokenService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/login")
    public JwtTokenResponseDto login(@Valid @RequestBody JwtTokenRequestDto dto) {
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                dto.username(), dto.password()
        );
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        return new JwtTokenResponseDto(jwtTokenService.generateJwtToken(dto.username()));
    }
}
