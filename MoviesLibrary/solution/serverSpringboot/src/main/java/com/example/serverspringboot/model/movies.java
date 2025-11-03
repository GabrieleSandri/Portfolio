package com.example.serverspringboot.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a movie.
 * <p>
 * Each movie contains detailed information such as title, release year,
 * description, tagline, and duration in minutes. It also holds relationships
 * with other entities such as actors, genres, countries, crew members, and Oscar awards.
 * </p>
 */
@Entity
public class movies {

    /**
     * Unique identifier of the movie.
     */
    @Id
    private Long id;

    /**
     * Name or title of the movie.
     */
    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String movieName;

    /**
     * Release year of the movie.
     */
    @Column(name = "date", nullable = false)
    private Integer date;

    /**
     * Tagline or slogan of the movie.
     */
    @Column(name = "tagline", nullable = false, columnDefinition = "TEXT")
    private String tagline;

    /**
     * Short description or synopsis of the movie.
     */
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    /**
     * Duration of the movie in minutes.
     */
    @Column(name = "minute")
    private Integer minute;

    /**
     * List of actors associated with the movie.
     */
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<actors> actors = new ArrayList<>();

    /**
     * List of genres associated with the movie.
     */
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<genres> genres = new ArrayList<>();

    /**
     * List of countries where the movie was released or produced.
     */
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<countries> countries = new ArrayList<>();

    /**
     * List of crew members involved in the movie.
     */
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<crews> crews = new ArrayList<>();

    /**
     * List of Oscar-winning crew members for this movie.
     * (This should probably point to a separate `oscarAwards` entity,
     * not `crews` again — consider revising this mapping.)
     */
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<crews> oscars = new ArrayList<>();

    // Constructors

    /**
     * Default constructor required by JPA.
     */
    public movies() {
    }

    /**
     * Constructor to initialize a movie with full attributes.
     *
     * @param id          unique ID of the movie
     * @param movieName   name or title of the movie
     * @param date        release year
     * @param tagline     promotional tagline
     * @param description movie synopsis
     * @param minute      duration in minutes
     */
    public movies(Long id, String movieName, int date, String tagline, String description, int minute) {
        this.id = id;
        this.movieName = movieName;
        this.date = date;
        this.tagline = tagline;
        this.description = description;
        this.minute = minute;
    }

    // Getters

    /**
     * Gets the unique ID of the movie.
     *
     * @return movie ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the name or title of the movie.
     *
     * @return movie name
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * Gets the release year of the movie.
     *
     * @return release year
     */
    public int getDate() {
        return date;
    }

    /**
     * Gets the movie's tagline.
     *
     * @return tagline
     */
    public String getTagline() {
        return tagline;
    }

    /**
     * Gets the description or synopsis of the movie.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the duration of the movie in minutes.
     *
     * @return duration in minutes
     */
    public int getMinute() {
        return minute;
    }

    // Setters

    /**
     * Sets the unique ID of the movie.
     *
     * @param id movie ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the name or title of the movie.
     *
     * @param movieName movie name to set
     */
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    /**
     * Sets the release year of the movie.
     *
     * @param date release year to set
     */
    public void setDate(int date) {
        this.date = date;
    }

    /**
     * Sets the tagline of the movie.
     *
     * @param tagline tagline to set
     */
    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    /**
     * Sets the description or synopsis of the movie.
     *
     * @param description description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the duration of the movie in minutes.
     *
     * @param minute duration to set
     */
    public void setMinute(int minute) {
        this.minute = minute;
    }
}
