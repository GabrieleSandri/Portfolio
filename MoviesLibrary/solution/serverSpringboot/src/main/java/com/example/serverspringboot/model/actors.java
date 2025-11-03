package com.example.serverspringboot.model;

import jakarta.persistence.*;

/**
 * Entity representing an actor associated with a movie.
 * <p>
 * This class is mapped to a table that stores information about actors,
 * including their name, role in the movie, and the associated {@link movies} entity.
 * </p>
 */
@Entity
public class actors {

    /**
     * Unique identifier for the actor.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The movie to which the actor is associated.
     * This is a many-to-one relationship.
     */
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private movies movie;

    /**
     * Name of the actor.
     */
    @Column(name = "actor_name", nullable = false, columnDefinition = "TEXT")
    private String name;

    /**
     * Role played by the actor in the movie.
     */
    @Column(name = "role", nullable = false, columnDefinition = "TEXT")
    private String role;

    /**
     * Default constructor required by JPA.
     */
    public actors() {
    }

    /**
     * Full constructor.
     *
     * @param id    the actor ID
     * @param name  the actor's name
     * @param role  the role played in the movie
     * @param movie the associated movie
     */
    public actors(Long id, String name, String role, movies movie) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.movie = movie;
    }

    /**
     * Gets the actor's ID.
     *
     * @return the actor's ID
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Gets the actor's name.
     *
     * @return the actor's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the role played by the actor.
     *
     * @return the role name
     */
    public String getRole() {
        return this.role;
    }

    /**
     * Gets the associated movie.
     *
     * @return the movie entity
     */
    public movies getMovieId() {
        return this.movie;
    }

    /**
     * Sets the actor's ID.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the actor's name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the role played by the actor.
     *
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Sets the associated movie.
     *
     * @param movie the movie to set
     */
    public void setMovieId(movies movie) {
        this.movie = movie;
    }
}
