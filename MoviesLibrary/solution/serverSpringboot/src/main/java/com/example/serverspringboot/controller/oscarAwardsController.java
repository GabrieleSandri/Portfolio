package com.example.serverspringboot.controller;

import com.example.serverspringboot.model.oscarAwards;
import com.example.serverspringboot.service.oscarAwardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing {@link oscarAwards} entities.
 * <p>
 * This controller handles HTTP requests related to Oscar Awards. It provides endpoints
 * for interacting with the {@link oscarAwardsService} which performs the business logic
 * for managing Oscar Awards.
 * </p>
 * <p>
 * All requests to this controller are mapped under the base path "/oscarAwards".
 * </p>
 */
@RestController
@RequestMapping("/oscarAwards")
public class oscarAwardsController {

    private final oscarAwardsService oscarAwardsService;

    /**
     * Constructor for injecting the {@link oscarAwardsService}.
     *
     * @param oscarAwardsService the service that handles Oscar Awards logic
     */
    @Autowired
    public oscarAwardsController(oscarAwardsService oscarAwardsService) {
        this.oscarAwardsService = oscarAwardsService;
    }

    /**
     * Retrieves a list of Oscar Awards associated with a specific movie ID.
     *
     * @param movie_id the ID of the movie
     * @return a list of Oscar Awards with HTTP status 200 if successful,
     *         or 500 if a server error occurs
     */
    @Operation(summary = "Find all the Oscar information by movie id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Founded"),
            @ApiResponse(responseCode = "204", description = "Not Founded"),
            @ApiResponse(responseCode = "500", description = "Internal server Error")
    })
    @GetMapping("/movie")
    public ResponseEntity<List<oscarAwards>> getAllOscarAwardsByMovieId(@RequestParam Long movie_id) {
        try {
            List<oscarAwards> awards = oscarAwardsService.getAllAwardsByMovieId(movie_id);
            return new ResponseEntity<>(awards, HttpStatus.OK);
        } catch (Exception e) {
            // Log exception if needed
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
