package com.gitDemo.movieLibraryDemo;

import com.gitDemo.movieLibraryDemo.customException.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieEntity>> getAllMovies() {
        try {
            List<MovieEntity> movies = movieService.getMovies();
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieEntity> getMovieById(@PathVariable Long id) {
        try {
            MovieEntity movie = movieService.getMovieById(id);
            if (movie != null) {
                return new ResponseEntity<>(movie, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<MovieEntity> addMovie(@RequestBody MovieEntity movie) {
        try {
            MovieEntity savedMovie = movieService.addMovie(movie);
            return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieEntity> updateMovie(@PathVariable Long id, @RequestBody MovieEntity movie) {
        try {
            movie.setId(id);
            MovieEntity updatedMovie = movieService.updateMovie(movie);
            return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MovieEntity> updateMoviePartially(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        try {
            MovieEntity updatedMovie = movieService.updateMoviePartially(id, updates);
            return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        try {
            movieService.deleteMovie(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
