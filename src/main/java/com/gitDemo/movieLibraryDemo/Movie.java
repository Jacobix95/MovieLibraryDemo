package com.gitDemo.movieLibraryDemo;

import java.util.Optional;

public record Movie(
        Long id,
        String title,
        Integer rating,
        Optional<String> director,
        Optional<Integer> releaseYear,
        Optional<String> genre
) {
    public Movie {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
    }
}
