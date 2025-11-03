package com.example.serverspringboot.service;

import com.example.serverspringboot.model.genres;
import com.example.serverspringboot.repository.genresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class that handles business logic related to {@link genres}.
 * Provides methods for retrieving, saving, and managing genre data.
 * <p>
 * This class acts as an intermediary between the controller layer
 * and the {@link genresRepository} (data access layer).
 * </p>
 */
@Service
public class genresService {

    private final genresRepository genresRepository;

    /**
     * Constructor for dependency injection of {@link genresRepository}.
     *
     * @param genresRepository repository for genre entities
     */
    @Autowired
    public genresService(genresRepository genresRepository) {
        this.genresRepository = genresRepository;
    }

    // You can add methods here to access genre data, e.g. findByMovieId, save, etc.
}

