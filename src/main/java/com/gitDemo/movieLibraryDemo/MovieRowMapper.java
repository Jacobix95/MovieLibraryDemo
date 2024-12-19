
package com.gitDemo.movieLibraryDemo;

import com.gitDemo.movieLibraryDemo.customException.DatabaseException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MovieRowMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet rs, int rowNum) {
        try {
            Long id = rs.getLong("id");
            String title = rs.getString("title");
            Integer rating = rs.getInt("rating");
            String director = rs.getString("director");
            Integer releaseYear = rs.getObject("release_year", Integer.class);
            String genre = rs.getString("genre");
            return new Movie(id, title, rating, Optional.ofNullable(director), Optional.ofNullable(releaseYear), Optional.ofNullable(genre));
        } catch (SQLException e) {
            throw new DatabaseException("Failed to map row to Movie object", e);
        }
    }
}
