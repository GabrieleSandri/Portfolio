package com.example.serverspringboot.service;

import com.example.serverspringboot.model.oscarAwards;
import com.example.serverspringboot.repository.oscarAwardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class that handles business logic related to {@link oscarAwards}.
 * Provides methods for retrieving, saving, and managing Oscar Awards data.
 * <p>
 * This class acts as an intermediary between the controller layer
 * and the {@link oscarAwardsRepository} (data access layer).
 * </p>
 */
@Service
public class oscarAwardsService {
    private final oscarAwardsRepository oscarAwardsRepository;

    /**
     * Constructor for dependency injection of {@link oscarAwardsRepository}.
     *
     * @param oscarAwardsRepository repository for Oscar Awards entities
     */
    @Autowired
    public oscarAwardsService(oscarAwardsRepository oscarAwardsRepository) {
        this.oscarAwardsRepository = oscarAwardsRepository;
    }

    /**
     * Retrieves all Oscar Awards associated with a specific movie ID.
     *
     * @param movieId the ID of the movie
     * @return a list of Oscar Awards for the specified movie
     */
    public List<oscarAwards> getAllAwardsByMovieId(Long movieId) {
        return this.oscarAwardsRepository.findByMovie_Id(movieId);
    }
}
