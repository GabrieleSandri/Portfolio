package com.example.serverspringboot.repository;

import com.example.serverspringboot.model.actors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 package com.example.serverspringboot.repository;

 import com.example.serverspringboot.model.actors;
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.stereotype.Repository;

 import java.util.List;

 /**
 * Repository interface for managing {@link actors} entities.
 * <p>
 * This interface extends {@link JpaRepository}, which provides basic CRUD
 * (Create, Read, Update, Delete) operations on the {@link actors} entity.
 * Spring Data JPA will automatically implement this interface at runtime.
 * </p>
 */
@Repository
public interface actorsRepository extends JpaRepository<actors, Long> {

    /**
     * Finds all actors associated with a specific movie by its ID.
     *
     * @param movie_id the ID of the movie
     * @return a list of {@link actors} associated with the given movie
     */
    List<actors> findByMovie_Id(Long movie_id);
}
