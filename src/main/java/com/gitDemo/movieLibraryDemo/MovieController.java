
package com.gitDemo.movieLibraryDemo;

import com.gitDemo.movieLibraryDemo.customException.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        try {
            List<Movie> movies = movieService.getMovies();
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable UUID id) {
        try {
            Movie movie = movieService.getMovieById(id);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Integer> addMovies(@RequestBody List<Movie> movies) {
        try {
            int result = movieService.addMovies(movies);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateMovie(@PathVariable UUID id, @RequestBody Movie movie) {
        try {
            movie = new Movie(id, movie.title(), movie.rating(), movie.director(), movie.releaseYear(), movie.genre());
            int result = movieService.updateMovie(movie);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Integer> updateMoviePartially(@PathVariable UUID id, @RequestBody Map<String, Object> updates) {
        try {
            int result = movieService.updateMoviePartially(id, updates);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteMovie(@PathVariable UUID id) {
        try {
            int result = movieService.deleteMovie(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
