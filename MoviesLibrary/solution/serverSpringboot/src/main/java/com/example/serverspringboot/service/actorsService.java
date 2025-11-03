package com.example.serverspringboot.service;

import com.example.serverspringboot.model.actors;
import com.example.serverspringboot.repository.actorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class that handles business logic related to {@link actors}.
 * Provides methods for retrieving, saving, and managing actor data.
 * <p>
 * This class acts as an intermediary between the controller layer
 * and the {@link actorsRepository} (data access layer).
 * </p>
 */
@Service
public class actorsService {
    private final actorsRepository actorRepository;

    @Autowired
    public actorsService(actorsRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    /**
     * Retrieves a list of actors associated with a specific movie ID.
     *
     * @param movieId the ID of the movie to get actors for
     * @return a list of {@link actors} entities related to the given movie
     */
    public List<actors> getAllActorsByMovieId(Long movieId) {
        return actorRepository.findByMovie_Id(movieId);
    }
}
