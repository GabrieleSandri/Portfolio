package com.example.serverspringboot.repository;

import com.example.serverspringboot.model.crews;
import com.example.serverspringboot.model.genres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link genres} entities.
 * <p>
 * This interface extends {@link JpaRepository}, which provides basic CRUD
 * (Create, Read, Update, Delete) operations on the {@link genres} entity.
 * Spring Data JPA will automatically implement this interface at runtime.
 * </p>
 */
@Repository
public interface genresRepository extends JpaRepository<genres, Long> {

}
