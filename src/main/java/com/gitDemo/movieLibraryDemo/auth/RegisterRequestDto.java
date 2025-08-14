package com.gitDemo.movieLibraryDemo.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDto(
        @Email @NotBlank String email,
        @NotBlank @Size(min = 6, message = "Password must have at least 6 chars")
        String password
) {}
