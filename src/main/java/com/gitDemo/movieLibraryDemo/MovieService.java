package com.gitDemo.movieLibraryDemo;

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

    public List<MovieEntity> getMovies() {
        try {
            return movieRepository.findAll(); // Changed from getAll() to findAll(), which is provided by JpaRepository
        } catch (Exception e) {
            throw new ServiceException("Failed to retrieve movies.", e);
        }
    }

    public MovieEntity getMovieById(Long id) {
        try {
            Optional<MovieEntity> movie = movieRepository.findById(id);
            return movie.orElseThrow(() -> new ServiceException("Movie not found"));
        } catch (Exception e) {
            throw new ServiceException("Failed to retrieve movie with id: " + id, e);
        }
    }

    public MovieEntity addMovie(MovieEntity movie) {
        try {
            return movieRepository.save(movie);
        } catch (Exception e) {
            throw new ServiceException("Failed to add movie.", e);
        }
    }

    public MovieEntity updateMovie(MovieEntity movie) {
        try {
            return movieRepository.save(movie);
        } catch (Exception e) {
            throw new ServiceException("Failed to update movie with id: " + movie.getId(), e);
        }
    }

    public MovieEntity updateMoviePartially(Long id, Map<String, Object> updates) {
        try {
            MovieEntity movie = movieRepository.findById(id)
                    .orElseThrow(() -> new ServiceException("Movie not found"));
            if (updates.containsKey("title")) {
                movie.setTitle((String) updates.get("title"));
            }
            if (updates.containsKey("rating")) {
                movie.setRating((Integer) updates.get("rating"));
            }
            if (updates.containsKey("director")) {
                movie.setDirector((String) updates.get("director"));
            }
            if (updates.containsKey("releaseYear")) {
                movie.setReleaseYear((Integer) updates.get("releaseYear"));
            }
            if (updates.containsKey("genre")) {
                movie.setGenre((String) updates.get("genre"));
            }
            return movieRepository.save(movie);
        } catch (Exception e) {
            throw new ServiceException("An unexpected error occurred while updating the movie", e);
        }
    }

    public void deleteMovie(Long id) {
        try {
            movieRepository.deleteById(id);
        } catch (Exception e) {
            throw new ServiceException("Failed to delete movie with id: " + id, e);
        }
    }
}
