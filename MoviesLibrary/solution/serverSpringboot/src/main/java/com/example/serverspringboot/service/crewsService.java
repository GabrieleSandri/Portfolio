package com.example.serverspringboot.service;

import com.example.serverspringboot.model.crews;
import com.example.serverspringboot.repository.crewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class that handles business logic related to {@link crews}.
 * Provides methods for retrieving, saving, and managing crew data.
 * <p>
 * This class acts as an intermediary between the controller layer
 * and the {@link crewsRepository} (data access layer).
 * </p>
 */
@Service
public class crewsService {
    private final crewsRepository crewsRepository;

    @Autowired
    public crewsService(crewsRepository crewsRepository) {
        this.crewsRepository = crewsRepository;
    }

    /**
     * Retrieves a list of crew members associated with a specific movie ID.
     *
     * @param movieId the ID of the movie to get crew members for
     * @return a list of {@link crews} entities related to the given movie
     */
    public List<crews> getAllCrewsByMovieId(Long movieId) {
        return crewsRepository.findByMovie_Id(movieId);
    }
}
