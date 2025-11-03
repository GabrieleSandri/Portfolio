package com.example.serverspringboot.controller;

import com.example.serverspringboot.model.movies;
import com.example.serverspringboot.service.moviesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * REST controller for managing {@link movies} entities.
 * <p>
 * This controller handles HTTP requests related to movies. It provides endpoints
 * for retrieving movies by ID, paginated lists of movies, and movies filtered by actor,
 * genre, or movie name.
 * </p>
 * <p>
 * All requests to this controller are mapped under the base path "/movies".
 * </p>
 */
@RestController
@RequestMapping("/movies")
public class moviesController {

    private final moviesService service;

    /**
     * Constructor for injecting the {@link moviesService}.
     *
     * @param service the service that contains business logic for movies
     */
    @Autowired
    public moviesController(moviesService service) {
        this.service = service;
    }

    /**
     * Retrieves a movie by its unique ID.
     *
     * @param id the ID of the movie to retrieve
     * @return the movie with HTTP status 200 if found, 404 if not found, or 400 if the ID is null,
     *         500 if a server error occurs
     */

    @Operation(summary = "Find one movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie Found"),
            @ApiResponse(responseCode = "404", description = "Movie Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal server Error")
    })
    @GetMapping("/id")
    public ResponseEntity<movies> findById(@RequestParam Long id) {
        try {
            if (id == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Optional<movies> movie = this.service.getMovieById(id);
            if (movie.isPresent()) {
                return new ResponseEntity<>(movie.get(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // log the error here if needed
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves a paginated list of all movies.
     *
     * @param page the page number to retrieve (default 0)
     * @param size the number of movies per page (default 12)
     * @return a paginated list of movies with HTTP status 200,
     *         204 if no movies found, or 500 if a server error occurs
     */
    @Operation(summary = "Find all the movies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movies Founded"),
            @ApiResponse(responseCode = "204", description = "Movies Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal server Error")
    })
    @GetMapping
    public ResponseEntity<Page<movies>> getAllMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size
    ) {
        try {
            Pageable pageable = getPageable(page, size);
            Page<movies> moviesList = this.service.getAllMovies(pageable);

            if (moviesList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(moviesList, HttpStatus.OK);
            }
        } catch (Exception e) {
            // log the error here if needed
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves a paginated list of movies filtered by actor name.
     *
     * @param actorName the name of the actor to filter by
     * @param page      the page number to retrieve (default 0)
     * @param size      the number of movies per page (default 12)
     * @return a paginated list of movies with HTTP status 200,
     *         204 if no movies found, or 500 if a server error occurs
     */
    @Operation(summary = "Find all the movies by actor's name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movies Founded"),
            @ApiResponse(responseCode = "204", description = "Movies Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal server Error")
    })
    @GetMapping("/actorsMovies")
    public ResponseEntity<Page<movies>> getAllMoviesByActor(
            @RequestParam String actorName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size
    ) {
        try {
            Pageable pageable = getPageable(page, size);
            Page<movies> moviesList = this.service.getMovieByActor(actorName, pageable);

            if (moviesList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(moviesList, HttpStatus.OK);
            }
        } catch (Exception e) {
            // log the error here if needed
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves a paginated list of movies filtered by genre name.
     *
     * @param genreName the name of the genre to filter by
     * @param page      the page number to retrieve (default 0)
     * @param size      the number of movies per page (default 12)
     * @return a paginated list of movies with HTTP status 200,
     *         204 if no movies found, or 500 if a server error occurs
     */
    @Operation(summary = "Find all the movies by genres")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movies Founded"),
            @ApiResponse(responseCode = "204", description = "Movies Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal server Error")
    })
    @GetMapping("/genresMovies")
    public ResponseEntity<Page<movies>> getAllMoviesByGenres(
            @RequestParam String genreName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size
    ) {
        try {
            Pageable pageable = getPageable(page, size);
            Page<movies> moviesList = this.service.getMovieByGenre(genreName, pageable);

            if (moviesList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(moviesList, HttpStatus.OK);
            }
        } catch (Exception e) {
            // log the error here if needed
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves a paginated list of movies filtered by movie name.
     *
     * @param movieName the name of the movie to filter by
     * @param page      the page number to retrieve (default 0)
     * @param size      the number of movies per page (default 12)
     * @return a paginated list of movies with HTTP status 200,
     *         204 if no movies found, or 500 if a server error occurs
     */

    @Operation(summary = "Find all the movies by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movies Founded"),
            @ApiResponse(responseCode = "204", description = "Movies Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal server Error")
    })
    @GetMapping("/name")
    public ResponseEntity<Page<movies>> getAllMoviesByMovieName(
            @RequestParam String movieName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size
    ) {
        try {
            Pageable pageable = getPageable(page, size);
            Page<movies> moviesList = this.service.getMovieByMovieName(movieName, pageable);

            if (moviesList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(moviesList, HttpStatus.OK);
            }
        } catch (Exception e) {
            // log the error here if needed
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Helper method to create a Pageable object with sorting by "id" ascending.
     *
     * @param page the page number
     * @param size the size of the page
     * @return a Pageable instance with sorting applied
     */
    public Pageable getPageable(int page, int size) {
        return PageRequest.of(page, size, Sort.by("id").ascending());
    }
}
