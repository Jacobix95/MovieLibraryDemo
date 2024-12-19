package com.gitDemo.movieLibraryDemo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class MovieTest {

    @Test
    @DisplayName("Given a valid Movie, When created, Then no exceptions should be thrown")
    void testValidMovieCreation() {
        // Given
        Long id = 12345678901L;
        String title = "Inception";
        Integer rating = 9;
        Optional<String> director = Optional.of("Christopher Nolan");
        Optional<Integer> releaseYear = Optional.of(2010);
        Optional<String> genre = Optional.of("Sci-Fi");

        // When
        Movie movie = new Movie(id, title, rating, director, releaseYear, genre);

        // Then
        Assertions.assertThat(movie).isNotNull();
        Assertions.assertThat(movie.title()).isEqualTo(title);
        Assertions.assertThat(movie.rating()).isEqualTo(rating);
        Assertions.assertThat(movie.director()).isEqualTo(director);
        Assertions.assertThat(movie.releaseYear()).isEqualTo(releaseYear);
        Assertions.assertThat(movie.genre()).isEqualTo(genre);
    }

    @Test
    @DisplayName("Given a null or blank title, When Movie is created, Then throw IllegalArgumentException")
    void testInvalidMovieTitle() {
        // Given
        Long id = 12345678901L;
        Integer rating = 8;
        Optional<String> director = Optional.of("Some Director");
        Optional<Integer> releaseYear = Optional.of(2020);
        Optional<String> genre = Optional.of("Drama");

        // When & Then - Null title
        Assertions.assertThatThrownBy(() -> new Movie(id, null, rating, director, releaseYear, genre))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Title is required");

        // When & Then - Blank title
        Assertions.assertThatThrownBy(() -> new Movie(id, "   ", rating, director, releaseYear, genre))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Title is required");
    }

    @Test
    @DisplayName("Given Optional fields are missing, When Movie is created, Then defaults to empty Optionals")
    void testMovieWithEmptyOptionalFields() {
        // Given
        Long id = 12345678901L;
        String title = "The Matrix";
        Integer rating = 10;

        // When
        Movie movie = new Movie(id, title, rating, Optional.empty(), Optional.empty(), Optional.empty());

        // Then
        Assertions.assertThat(movie).isNotNull();
        Assertions.assertThat(movie.director()).isEmpty();
        Assertions.assertThat(movie.releaseYear()).isEmpty();
        Assertions.assertThat(movie.genre()).isEmpty();
    }

    @Test
    @DisplayName("Given valid Movie attributes, When created, Then all values should be set correctly")
    void testMovieAttributes() {
        // Given
        Long id = 12345633901L;
        String title = "The Dark Knight";
        Integer rating = 9;
        Optional<String> director = Optional.of("Christopher Nolan");
        Optional<Integer> releaseYear = Optional.of(2008);
        Optional<String> genre = Optional.of("Action");

        // When
        Movie movie = new Movie(id, title, rating, director, releaseYear, genre);

        // Then
        Assertions.assertThat(movie.id()).isEqualTo(id);
        Assertions.assertThat(movie.title()).isEqualTo(title);
        Assertions.assertThat(movie.rating()).isEqualTo(rating);
        Assertions.assertThat(movie.director()).isEqualTo(director);
        Assertions.assertThat(movie.releaseYear()).isEqualTo(releaseYear);
        Assertions.assertThat(movie.genre()).isEqualTo(genre);
    }
}
