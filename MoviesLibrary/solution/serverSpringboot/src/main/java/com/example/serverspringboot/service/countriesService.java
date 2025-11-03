package com.example.serverspringboot.service;

import com.example.serverspringboot.model.countries;
import com.example.serverspringboot.repository.countriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class that handles business logic related to {@link countries}.
 * Provides methods for retrieving, saving, and managing country data.
 * <p>
 * This class acts as an intermediary between the controller layer
 * and the {@link countriesRepository} (data access layer).
 * </p>
 */
@Service
public class countriesService {
    private final countriesRepository countryRepository;

    @Autowired
    public countriesService(countriesRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    /**
     * Retrieves a list of countries associated with a specific movie ID.
     *
     * @param movieId the ID of the movie to get countries for
     * @return a list of {@link countries} entities related to the given movie
     */
    public List<countries> getAllCountriesByMovieId(Long movieId) {
        return countryRepository.findByMovie_Id(movieId);
    }
}
