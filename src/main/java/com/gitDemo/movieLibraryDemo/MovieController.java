package com.gitDemo.movieLibraryDemo;

import com.gitDemo.movieLibraryDemo.customException.ServiceException;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.gitDemo.movieLibraryDemo.auth.config.SpringSecurityConfig.DEVELOPER_READ;
import static com.gitDemo.movieLibraryDemo.auth.config.SpringSecurityConfig.DEVELOPER_WRITE;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    @RolesAllowed(DEVELOPER_READ)
    public ResponseEntity<List<MovieEntity>> getAllMovies() {
        try {
            List<MovieEntity> movies = movieService.getMovies();
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/top")
    @RolesAllowed(DEVELOPER_READ)
    public ResponseEntity<List<MovieEntity>> getTop3Movies() {
        try {
            List<MovieEntity> topMovies = movieService.getTop3Movies();
            return new ResponseEntity<>(topMovies, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    @RolesAllowed(DEVELOPER_READ)
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
    @RolesAllowed(DEVELOPER_READ)
    public ResponseEntity<MovieEntity> addMovie(@RequestBody MovieEntity movie) {
        try {
            MovieEntity savedMovie = movieService.addMovie(movie);
            return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @RolesAllowed(DEVELOPER_WRITE)
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
    @RolesAllowed(DEVELOPER_WRITE)
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
    @RolesAllowed(DEVELOPER_WRITE)
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
