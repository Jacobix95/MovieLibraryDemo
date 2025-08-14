package com.gitDemo.movieLibraryDemo.auth;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/auth")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtTokenResponseDto register(@Valid @RequestBody RegisterRequestDto dto) {
        return registrationService.register(dto);
    }

    @ExceptionHandler(RegistrationService.EmailAlreadyUsedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleEmailInUse(RegistrationService.EmailAlreadyUsedException ex) {
        return ex.getMessage();
    }
}