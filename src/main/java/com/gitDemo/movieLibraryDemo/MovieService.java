package com.gitDemo.movieLibraryDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final IMovieRepository movieRepository;

    @Autowired
    public MovieService(IMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.getAll();
    }

    public Movie getMovieById(int id) {
        return movieRepository.getById(id);
    }

    public int addMovies(List<Movie> movies) {
        return movieRepository.save(movies);
    }

    public boolean updateMovie(int id, Movie updatedMovie) {
        Movie existingMovie = movieRepository.getById(id);
        if (existingMovie != null) {
            existingMovie.setTitle(updatedMovie.getTitle());
            existingMovie.setRating(updatedMovie.getRating());
            movieRepository.update(existingMovie);
            return true;
        }
        return false;
    }

    public boolean partiallyUpdateMovie(int id, Movie updatedMovie) {
        Movie existingMovie = movieRepository.getById(id);
        if (existingMovie != null) {
            if (updatedMovie.getTitle() != null) existingMovie.setTitle(updatedMovie.getTitle());
            if (updatedMovie.getRating() > 0) existingMovie.setRating(updatedMovie.getRating());
            movieRepository.update(existingMovie);
            return true;
        }
        return false;
    }

    public boolean deleteMovie(int id) {
        return movieRepository.delete(id) > 0;
    }
}
