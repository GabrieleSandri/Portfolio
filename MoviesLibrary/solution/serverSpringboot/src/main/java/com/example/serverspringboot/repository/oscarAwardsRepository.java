package com.example.serverspringboot.repository;

import com.example.serverspringboot.model.oscarAwards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link oscarAwards} entities.
 * <p>
 * This interface extends {@link JpaRepository}, which provides basic CRUD
 * (Create, Read, Update, Delete) operations on the {@link oscarAwards} entity.
 * Spring Data JPA will automatically implement this interface at runtime.
 * </p>
 */
@Repository
public interface oscarAwardsRepository extends JpaRepository<oscarAwards, Long> {

    /**
     * Retrieves all Oscar awards associated with a given movie ID.
     *
     * @param movie_id the ID of the movie whose Oscar awards are to be retrieved
     * @return a list of {@link oscarAwards} entities linked to the specified movie
     */
    List<oscarAwards> findByMovie_Id(Long movie_id);
}
