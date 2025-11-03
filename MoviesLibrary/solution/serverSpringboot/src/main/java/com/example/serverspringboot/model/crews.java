package com.example.serverspringboot.model;

import jakarta.persistence.*;

/**
 * Entity representing a crew member associated with a movie.
 * <p>
 * Each crew member is linked to a specific movie and has a defined role in the movie's production.
 * </p>
 */
@Entity
public class crews {

    /**
     * Unique identifier for the crew member.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the crew member.
     */
    @Column(name = "member_name", nullable = false, columnDefinition = "TEXT")
    private String name;

    /**
     * The movie associated with this crew member.
     * This is a many-to-one relationship.
     */
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private movies movie;

    /**
     * The role of the crew member in the movie.
     */
    @Column(name = "role", nullable = false, columnDefinition = "TEXT")
    private String role;

    /**
     * Default constructor required by JPA.
     */
    public crews() {
    }

    /**
     * Full constructor for creating a crew member entity.
     *
     * @param id    the unique ID of the crew member
     * @param name  the name of the crew member
     * @param movie the associated movie
     * @param role  the role of the crew member
     */
    public crews(Long id, String name, movies movie, String role) {
        this.id = id;
        this.name = name;
        this.movie = movie;
        this.role = role;
    }

    // Getters

    /**
     * Gets the ID of the crew member.
     *
     * @return the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the name of the crew member.
     *
     * @return the name
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

    /**
     * Gets the role of the crew member.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    // Setters

    /**
     * Sets the ID of the crew member.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the name of the crew member.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the associated movie.
     *
     * @param movie the movie entity to associate
     */
    public void setMovieId(movies movie) {
        this.movie = movie;
    }

    /**
     * Sets the role of the crew member.
     *
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }
}
