package com.gitDemo.movieLibraryDemo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "movie")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Size(max = 200)
    @Column(nullable = false, length = 200)
    private String title;

    @Size(max = 200)
    @Column(length = 200)
    private String director;

    @Column(name = "release_year")
    private Integer releaseYear;

    @Size(max = 200)
    @Column(length = 200)
    private String genre;

    @Column
    private Integer rating;

    @Column
    private Integer version;
}
