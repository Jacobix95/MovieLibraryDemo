package com.gitDemo.movieLibraryDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getMovies() {
        return movieRepository.getAll();
    }

    public Movie getMovieById(UUID id) {
        return movieRepository.getById(id);
    }

    public int addMovies(List<Movie> movies) {
        return movieRepository.save(movies);
    }

    public int updateMovie(Movie movie) {
        return movieRepository.update(movie);
    }

    public int deleteMovie(UUID id) {
        return movieRepository.delete(id);
    }
}
