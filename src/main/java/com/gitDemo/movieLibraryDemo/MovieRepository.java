package com.gitDemo.movieLibraryDemo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public class MovieRepository {
    private final JdbcTemplate jdbcTemplate;

    public MovieRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Movie> getAll() {
        return jdbcTemplate.query("SELECT id, title, rating, director, release_year, genre FROM movie",
                new MovieRowMapper());
    }

    public Movie getById(UUID id) {
        try {
            return jdbcTemplate.queryForObject("SELECT id, title, rating, director, release_year, genre FROM movie WHERE id=?",
                    new MovieRowMapper(), id.toString());
        } catch (Exception e) {
            return null;
        }
    }

    public int save(List<Movie> movies) {
        movies.forEach(movie -> jdbcTemplate.update(
                "INSERT INTO movie(id, title, rating, director, release_year, genre) VALUES(?, ?, ?, ?, ?, ?)",
                movie.id().toString(),
                movie.title(),
                movie.rating(),
                movie.director().orElse(null),
                movie.releaseYear().orElse(null),
                movie.genre().orElse(null)
        ));
        return movies.size();
    }

    public int update(Movie movie) {
        return jdbcTemplate.update(
                "UPDATE movie SET title=?, rating=?, director=?, release_year=?, genre=? WHERE id=?",
                movie.title(),
                movie.rating(),
                movie.director().orElse(null),
                movie.releaseYear().orElse(null),
                movie.genre().orElse(null),
                movie.id().toString()
        );
    }

    public int delete(UUID id) {
        return jdbcTemplate.update("DELETE FROM movie WHERE id=?", id.toString());
    }
}
