package com.example.serverspringboot.repository;

import com.example.serverspringboot.model.movies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link movies} entities.
 * <p>
 * This interface extends {@link JpaRepository}, which provides basic CRUD
 * (Create, Read, Update, Delete) operations on the {@link movies} entity.
 * Spring Data JPA will automatically implement this interface at runtime.
 * </p>
 */
@Repository
public interface moviesRepository extends JpaRepository<movies, Long> {

    /**
     * Retrieves a page of movies where the actor's name contains the specified string.
     *
     * @param name     the substring to search in actor names
     * @param pageable pagination information
     * @return a page of movies matching the actor name criteria
     */
    Page<movies> findByActorsNameContains(String name, Pageable pageable);

    /**
     * Retrieves a page of movies where the genre's name contains the specified string.
     *
     * @param genresName the substring to search in genre names
     * @param pageable   pagination information
     * @return a page of movies matching the genre name criteria
     */
    Page<movies> findByGenresNameContains(String genresName, Pageable pageable);

    /**
     * Retrieves a page of movies where the movie name contains the specified string.
     *
     * @param movieName the substring to search in movie names
     * @param pageable  pagination information
     * @return a page of movies matching the movie name criteria
     */
    Page<movies> findByMovieNameContains(String movieName, Pageable pageable);

}
