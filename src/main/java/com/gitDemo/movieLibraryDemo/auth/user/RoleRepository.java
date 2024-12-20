package com.gitDemo.movieLibraryDemo.auth.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r FROM Role r JOIN FETCH r.users WHERE r.name = :name")
    Optional<Role> findByName(String name);
}