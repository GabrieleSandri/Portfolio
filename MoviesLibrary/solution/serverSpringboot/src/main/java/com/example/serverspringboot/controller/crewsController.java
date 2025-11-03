package com.example.serverspringboot.controller;

import com.example.serverspringboot.model.crews;
import com.example.serverspringboot.service.crewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing {@link crews} entities.
 * <p>
 * This controller handles HTTP requests related to crews. It provides endpoints for
 * interacting with the {@link crewsService} which contains the business logic
 * for managing crew data.
 * </p>
 * <p>
 * All requests to this controller are mapped under the base path "/crews".
 * </p>
 */
@RestController
@RequestMapping("/crews")
public class crewsController {

    private final crewsService crewsService;

    /**
     * Constructor to inject {@link crewsService}.
     *
     * @param crewsService the service handling crew-related business logic
     */
    @Autowired
    public crewsController(crewsService crewsService) {
        this.crewsService = crewsService;
    }

    /**
     * Retrieves a list of crews associated with a specific movie identified by {@code movie_id}.
     *
     * @param movie_id the ID of the movie whose crews are requested
     * @return a {@link ResponseEntity} containing a list of crews and HTTP status code 200 if successful,
     *         or HTTP status code 500 in case of server errors
     */
    @Operation(summary = "Find all crew information by movie id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Founded"),
            @ApiResponse(responseCode = "500", description = "Internal server Error")
    })
    @GetMapping("/movie")
    public ResponseEntity<List<crews>> getAllCrewsByMovieId(@RequestParam Long movie_id) {
        try {
            List<crews> crewsList = crewsService.getAllCrewsByMovieId(movie_id);
            return new ResponseEntity<>(crewsList, HttpStatus.OK);
        } catch (Exception e) {
            // You can add logging here to record the exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
