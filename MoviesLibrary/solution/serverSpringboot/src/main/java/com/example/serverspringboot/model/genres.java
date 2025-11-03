package com.example.serverspringboot.model;

import jakarta.persistence.*;

/**
 * Entity representing a genre associated with a movie.
 * <p>
 * Each genre is linked to a specific movie and has a name.
 * </p>
 */
@Entity
public class genres {

    /**
     * Unique identifier for the genre.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The movie to which this genre belongs.
     */
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private movies movie;

    /**
     * The name of the genre (e.g., Drama, Comedy, Action).
     */
    @Column(name = "genre_name", nullable = false, columnDefinition = "TEXT")
    private String name;

    // Constructors

    /**
     * Default constructor required by JPA.
     */
    public genres() {
    }

    /**
     * Constructor to initialize genre with a movie and a name.
     *
     * @param movie the movie associated with this genre
     * @param name  the name of the genre
     */
    public genres(movies movie, String name) {
        this.movie = movie;
        this.name = name;
    }

    // Getters

    /**
     * Gets the ID of the genre.
     *
     * @return the genre ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the name of the genre.
     *
     * @return the genre name
     */
    public String getGenreName() {
        return name;
    }

    /**
     * Gets the movie associated with this genre.
     *
     * @return the movie entity
     */
    public movies getMovie() {
        return movie;
    }

    // Setters

    /**
     * Sets the ID of the genre.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the name of the genre.
     *
     * @param name the genre name to set
     */
    public void setGenreName(String name) {
        this.name = name;
    }

    /**
     * Sets the movie associated with this genre.
     *
     * @param movie the movie to associate
     */
    public void setMovie(movies movie) {
        this.movie = movie;
    }
}
