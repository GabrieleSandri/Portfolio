package com.example.serverspringboot.service;

import com.example.serverspringboot.model.movies;
import com.example.serverspringboot.repository.moviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class that handles business logic related to {@link movies}.
 * Provides methods for retrieving, saving, and managing movie data.
 * <p>
 * This class acts as an intermediary between the controller layer
 * and the {@link moviesRepository} (data access layer).
 * </p>
 */
@Service
public class moviesService {
    private final moviesRepository moviesRepository;

    /**
     * Constructor for dependency injection of {@link moviesRepository}.
     *
     * @param moviesRepository repository for movie entities
     */
    @Autowired
    public moviesService(moviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    /**
     * Retrieves all movies with pagination.
     *
     * @param pageable pagination information
     * @return a page of movies
     */
    public Page<movies> getAllMovies(Pageable pageable) {
        return moviesRepository.findAll(pageable);
    }

    /**
     * Retrieves a movie by its ID.
     *
     * @param id the movie's ID
     * @return an Optional containing the movie if found, or empty otherwise
     */
    public Optional<movies> getMovieById(Long id) {
        return this.moviesRepository.findById(id);
    }

    /**
     * Retrieves movies that contain the given actor name.
     *
     * @param actorName the actor's name to search for
     * @param pageable pagination information
     * @return a page of movies containing the actor
     */
    public Page<movies> getMovieByActor(String actorName, Pageable pageable) {
        return moviesRepository.findByActorsNameContains(actorName, pageable);
    }

    /**
     * Retrieves movies that belong to the given genre.
     *
     * @param genreName the genre name to search for
     * @param pageable pagination information
     * @return a page of movies of the specified genre
     */
    public Page<movies> getMovieByGenre(String genreName, Pageable pageable) {
        return moviesRepository.findByGenresNameContains(genreName, pageable);
    }

    /**
     * Retrieves movies that contain the given movie name.
     *
     * @param movieName the movie name to search for
     * @param pageable pagination information
     * @return a page of movies matching the name
     */
    public Page<movies> getMovieByMovieName(String movieName, Pageable pageable) {
        return moviesRepository.findByMovieNameContains(movieName, pageable);
    }
}
