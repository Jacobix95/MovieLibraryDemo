
package com.gitDemo.movieLibraryDemo;

import com.gitDemo.movieLibraryDemo.customException.DatabaseException;
import com.gitDemo.movieLibraryDemo.customException.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getMovies() {
        try {
            return movieRepository.getAll();
        } catch (DatabaseException e) {
            throw new ServiceException("Failed to retrieve movies.", e);
        } catch (Exception e) {
            throw new ServiceException("An unexpected error occurred while retrieving movies.", e);
        }
    }

    public Movie getMovieById(UUID id) {
        try {
            return movieRepository.getById(id);
        } catch (DatabaseException e) {
            throw new ServiceException("Failed to retrieve movie with id: " + id, e);
        } catch (Exception e) {
            throw new ServiceException("An unexpected error occurred while retrieving movie with id: " + id, e);
        }
    }

    public int addMovies(List<Movie> movies) {
        try {
            return movieRepository.save(movies);
        } catch (DatabaseException e) {
            throw new ServiceException("Failed to add movies.", e);
        } catch (Exception e) {
            throw new ServiceException("An unexpected error occurred while adding movies.", e);
        }
    }

    public int updateMovie(Movie movie) {
        try {
            return movieRepository.update(movie);
        } catch (DatabaseException e) {
            throw new ServiceException("Failed to update movie with id: " + movie.id(), e);
        } catch (Exception e) {
            throw new ServiceException("An unexpected error occurred while updating movie with id: " + movie.id(), e);
        }
    }

    public int updateMoviePartially(UUID id, Map<String, Object> updates) {
        try {
            Movie movie = movieRepository.getById(id);
            if (movie == null) {
                throw new ServiceException("Movie not found");
            }

            // Create a new record with updated fields or existing fields if not updated
            Movie updatedMovie = new Movie(
                    movie.id(), // ID should remain the same
                    (String) updates.getOrDefault("title", movie.title()),
                    (Integer) updates.getOrDefault("rating", movie.rating()),
                    Optional.ofNullable((String) updates.getOrDefault("director", movie.director().orElse(null))),
                    Optional.ofNullable((Integer) updates.getOrDefault("releaseYear", movie.releaseYear().orElse(null))),
                    Optional.ofNullable((String) updates.getOrDefault("genre", movie.genre().orElse(null)))
            );

            return movieRepository.update(updatedMovie);
        } catch (DatabaseException e) {
            throw new ServiceException("Failed to update movie with id: " + id, e);
        }
    }


    public int deleteMovie(UUID id) {
        try {
            return movieRepository.delete(id);
        } catch (DatabaseException e) {
            throw new ServiceException("Failed to delete movie with id: " + id, e);
        } catch (Exception e) {
            throw new ServiceException("An unexpected error occurred while deleting movie with id: " + id, e);
        }
    }
}
