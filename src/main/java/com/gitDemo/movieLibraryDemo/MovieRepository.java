
package com.gitDemo.movieLibraryDemo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.gitDemo.movieLibraryDemo.customException.DatabaseException;

import java.util.List;
import java.util.UUID;

@Repository
public class MovieRepository {
    private final JdbcTemplate jdbcTemplate;

    public MovieRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Movie> getAll() {
        try {
            return jdbcTemplate.query("SELECT id, title, rating, director, release_year, genre FROM movie",
                    new MovieRowMapper());
        } catch (Exception e) {
            throw new DatabaseException("Failed to retrieve movies.", e);
        }
    }

    public Movie getById(UUID id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT id, title, rating, director, release_year, genre FROM movie WHERE id=?",
                    new MovieRowMapper(),
                    id.toString()
            );
        } catch (Exception e) {
            throw new DatabaseException("Failed to retrieve movie with id: " + id, e);
        }
    }

    public int save(List<Movie> movies) {
        try {
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
        } catch (Exception e) {
            throw new DatabaseException("Failed to save movies.", e);
        }
    }

    public int update(Movie movie) {
        try {
            return jdbcTemplate.update(
                    "UPDATE movie SET title=?, rating=?, director=?, release_year=?, genre=? WHERE id=?",
                    movie.title(),
                    movie.rating(),
                    movie.director().orElse(null),
                    movie.releaseYear().orElse(null),
                    movie.genre().orElse(null),
                    movie.id().toString()
            );
        } catch (Exception e) {
            throw new DatabaseException("Failed to update movie with id: " + movie.id(), e);
        }
    }

    public int delete(UUID id) {
        try {
            return jdbcTemplate.update("DELETE FROM movie WHERE id=?", id.toString());
        } catch (Exception e) {
            throw new DatabaseException("Failed to delete movie with id: " + id, e);
        }
    }
}
