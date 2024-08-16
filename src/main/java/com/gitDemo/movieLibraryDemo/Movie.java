package com.gitDemo.movieLibraryDemo;

import java.util.Optional;
import java.util.UUID;

public record Movie(
        UUID id,
        String title,
        Integer rating,
        Optional<String> director,
        Optional<Integer> releaseYear,
        Optional<String> genre
) {
    // Constructor to ensure title is not blank
    public Movie {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
    }
}
