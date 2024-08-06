package com.gitDemo.movieLibraryDemo;

import java.util.List;

public interface IMovieRepository {
    List<Movie> getAll();

    Movie getById(int id);

    int save(List<Movie> movies);

    int update(Movie movie);

    int delete(int id);
}
