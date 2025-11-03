package com.example.serverspringboot.controller;

import com.example.serverspringboot.service.genresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing genres.
 * <p>
 * This controller handles HTTP requests related to genres. It interacts with
 * the {@link genresService} which contains the business logic for managing genres.
 * </p>
 * <p>
 * All requests to this controller are mapped under the base path "/genres".
 * </p>
 */
@RestController
@RequestMapping("/genres")
public class genresController {

    private final genresService service;

    /**
     * Constructor for injecting the {@link genresService}.
     *
     * @param service the service responsible for genre-related operations
     */
    @Autowired
    public genresController(genresService service) {
        this.service = service;
    }

    // No endpoints defined yet. Adding methods here to expose genre-related APIs.

}
