package com.example.serverspringboot.repository;

import com.example.serverspringboot.model.crews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link crews} entities.
 * <p>
 * This interface extends {@link JpaRepository}, which provides basic CRUD
 * (Create, Read, Update, Delete) operations on the {@link crews} entity.
 * Spring Data JPA will automatically implement this interface at runtime.
 * </p>
 */
@Repository
public interface crewsRepository extends JpaRepository<crews, Long> {

    /**
     * Retrieves all crews associated with a specific movie by its ID.
     *
     * @param movie_id the ID of the movie
     * @return a list of {@link crews} entities related to the given movie
     */
    List<crews> findByMovie_Id(Long movie_id);

}
