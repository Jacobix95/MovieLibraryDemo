package com.gitDemo.movieLibraryDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getMovies();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable UUID id) {
        return movieService.getMovieById(id);
    }

    @PostMapping
    public int addMovies(@RequestBody List<Movie> movies) {
        return movieService.addMovies(movies);
    }

    @PutMapping("/{id}")
    public int updateMovie(@PathVariable UUID id, @RequestBody Movie movie) {
        movie = new Movie(id, movie.title(), movie.rating(), movie.director(), movie.releaseYear(), movie.genre());
        return movieService.updateMovie(movie);
    }

    @DeleteMapping("/{id}")
    public int deleteMovie(@PathVariable UUID id) {
        return movieService.deleteMovie(id);
    }
}
