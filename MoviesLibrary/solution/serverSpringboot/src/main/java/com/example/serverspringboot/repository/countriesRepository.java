package com.example.serverspringboot.repository;

import com.example.serverspringboot.model.countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link countries} entities.
 * <p>
 * This interface extends {@link JpaRepository}, which provides basic CRUD
 * (Create, Read, Update, Delete) operations on the {@link countries} entity.
 * Spring Data JPA will automatically implement this interface at runtime.
 * </p>
 */
@Repository
public interface countriesRepository extends JpaRepository<countries, Long> {

    /**
     * Retrieves all countries associated with a specific movie by its ID.
     *
     * @param movie_id the ID of the movie
     * @return a list of {@link countries} entities related to the given movie
     */
    List<countries> findByMovie_Id(Long movie_id);
}
