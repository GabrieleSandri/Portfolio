package com.example.serverspringboot.controller;

import com.example.serverspringboot.model.countries;
import com.example.serverspringboot.service.countriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing {@link countries} entities.
 * <p>
 * This controller provides endpoints to retrieve countries associated with movies.
 * It delegates business logic to {@link countriesService}.
 * </p>
 * <p>
 * All HTTP requests handled by this controller are mapped under the base path "/countries".
 * </p>
 */
@RestController
@RequestMapping("/countries")
public class countriesController {

    private final countriesService countriesService;

    /**
     * Constructor to inject {@link countriesService}.
     *
     * @param countriesService the service handling countries-related business logic
     */
    @Autowired
    public countriesController(countriesService countriesService) {
        this.countriesService = countriesService;
    }

    /**
     * Retrieves a list of countries associated with a specific movie identified by {@code movie_id}.
     *
     * @param movie_id the ID of the movie whose countries are requested
     * @return a {@link ResponseEntity} containing a list of countries and HTTP status code 200 if successful,
     *         or HTTP status code 500 in case of server errors
     */
    @Operation(summary = "Find all countries information by movie id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Founded"),
            @ApiResponse(responseCode = "500", description = "Internal server Error")
    })
    @GetMapping("/movie")
    public ResponseEntity<List<countries>> getAllCountriesByMovieId(@RequestParam Long movie_id) {
        try {
            List<countries> countriesList = countriesService.getAllCountriesByMovieId(movie_id);
            return new ResponseEntity<>(countriesList, HttpStatus.OK);
        } catch (Exception e) {
            // Ideally log the exception here
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
