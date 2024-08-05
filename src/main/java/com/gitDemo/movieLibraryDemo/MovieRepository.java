package com.gitDemo.movieLibraryDemo;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepository implements IMovieRepository {
    private final JdbcTemplate jdbcTemplate;

    public MovieRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> getAll() {
        return jdbcTemplate.query("SELECT id, title, rating FROM movie",
                BeanPropertyRowMapper.newInstance(Movie.class));
    }

    @Override
    public Movie getById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT id, title, rating FROM movie WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Movie.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int save(List<Movie> movies) {
        movies.forEach(movie -> jdbcTemplate.update(
                "INSERT INTO movie(title, rating) VALUES(?, ?)",
                movie.getTitle(), movie.getRating()));

        return movies.size();
    }

    @Override
    public int update(Movie movie) {
        return jdbcTemplate.update(
                "UPDATE movie SET title=?, rating=? WHERE id=?",
                movie.getTitle(), movie.getRating(), movie.getId());
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM movie WHERE id=?", id);
    }
}