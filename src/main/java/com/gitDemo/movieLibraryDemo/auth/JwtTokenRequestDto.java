package com.gitDemo.movieLibraryDemo.auth;

import jakarta.validation.constraints.NotBlank;

public record JwtTokenRequestDto(
        @NotBlank(message = "username cannot be empty")
        String username,
        @NotBlank(message = "password cannot be empty")
        String password
) {
}
