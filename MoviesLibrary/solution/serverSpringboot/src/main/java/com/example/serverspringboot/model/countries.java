package com.example.serverspringboot.model;

import jakarta.persistence.*;

/**
 * Entity representing a country associated with a movie.
 * <p>
 * Each country entry refers to one movie and indicates a country involved in the production or distribution.
 * </p>
 */
@Entity
public class countries {

    /**
     * Unique identifier for the country entry.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The movie to which this country is related.
     * This is a many-to-one relationship.
     */
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private movies movie;

    /**
     * The name of the country.
     */
    @Column(name = "country_name", nullable = false, columnDefinition = "TEXT")
    private String name;

    /**
     * Default constructor required by JPA.
     */
    public countries() {}

    /**
     * Full constructor for creating a country entry.
     *
     * @param id    the unique ID of the country entry
     * @param movie the associated movie
     * @param name  the country name
     */
    public countries(Long id, movies movie, String name) {
        this.id = id;
        this.movie = movie;
        this.name = name;
    }

    // Getters

    /**
     * Gets the ID of the country entry.
     *
     * @return the country ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the name of the country.
     *
     * @return the country name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the associated movie.
     *
     * @return the movie entity
     */
    public movies getMovie() {
        return movie;
    }

    // Setters

    /**
     * Sets the ID of the country entry.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the name of the country.
     *
     * @param name the country name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the associated movie.
     *
     * @param movie the movie entity to associate
     */
    public void setMovie(movies movie) {
        this.movie = movie;
    }
}
