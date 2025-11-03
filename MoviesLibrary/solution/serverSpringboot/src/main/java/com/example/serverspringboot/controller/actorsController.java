package com.example.serverspringboot.controller;

import com.example.serverspringboot.model.actors;
import com.example.serverspringboot.service.actorsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing {@link actors} entities.
 * <p>
 * This controller provides endpoints to retrieve actors associated with movies.
 * It delegates business logic to {@link actorsService}.
 * </p>
 * <p>
 * All HTTP requests handled by this controller are mapped under the base path "/actors".
 * </p>
 */
@RestController
@RequestMapping("/actors")
public class actorsController {

    private final actorsService actorsService;

    /**
     * Constructor to inject {@link actorsService}.
     *
     * @param actorsService the service handling actors-related business logic
     */
    @Autowired
    public actorsController(actorsService actorsService) {
        this.actorsService = actorsService;
    }

    /**
     * Retrieves a list of actors associated with a specific movie identified by {@code movie_id}.
     *
     * @param movie_id the ID of the movie whose actors are requested
     * @return a {@link ResponseEntity} containing a list of actors and HTTP status code 200 if successful,
     *         or HTTP status code 500 in case of server errors
     */
    @Operation(summary = "Find all the actors information by movie id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Founded"),
            @ApiResponse(responseCode = "500", description = "Internal server Error")
    })
    @GetMapping("/movie")
    public ResponseEntity<List<actors>> getAllActorsByMovieId(@RequestParam Long movie_id) {
        try {
            List<actors> actorsList = actorsService.getAllActorsByMovieId(movie_id);
            return new ResponseEntity<>(actorsList, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception here if you have a logger (e.g., Logger.error("Error fetching actors", e))
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
