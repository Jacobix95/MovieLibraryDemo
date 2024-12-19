
package com.gitDemo.movieLibraryDemo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    @Query("SELECT m FROM MovieEntity m")
    List<MovieEntity> getAll();

    @Query("SELECT m FROM MovieEntity m WHERE m.id = :id")
    MovieEntity getById(Long id);
}