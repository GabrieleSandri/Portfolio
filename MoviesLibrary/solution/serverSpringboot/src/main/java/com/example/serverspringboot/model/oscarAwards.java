package com.example.serverspringboot.model;

import jakarta.persistence.*;

/**
 * Entity representing an Oscar award nomination or win.
 * <p>
 * Contains data such as the ceremony year, category, nominee name,
 * whether it was a win, and the associated movie.
 * </p>
 */
@Entity
public class oscarAwards {

    /**
     * Unique identifier of the Oscar award entry.
     */
    @Id
    private Long id;

    /**
     * Year in which the ceremony took place.
     */
    @Column(name = "year_ceremony", nullable = false)
    private int yearCeremony;

    /**
     * Category of the Oscar award (e.g., Best Actor, Best Picture).
     */
    @Column(name = "category", nullable = false, columnDefinition = "TEXT")
    private String category;

    /**
     * Name of the person nominated (e.g., actor, director).
     */
    @Column(name = "actor_name", nullable = false, columnDefinition = "TEXT")
    private String actorName;

    /**
     * Ceremony number (e.g., 95 for the 95th Academy Awards).
     */
    @Column(name = "ceremony", nullable = false)
    private Integer ceremony;

    /**
     * True if the nominee won the Oscar, false if only nominated.
     */
    @Column(name = "winner", nullable = false, columnDefinition = "BOOLEAN")
    private boolean winner;

    /**
     * Reference to the movie associated with the Oscar nomination.
     */
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private movies movie;

    // Constructors

    /**
     * Default constructor required by JPA.
     */
    public oscarAwards() {}

    /**
     * Full constructor for the Oscar award entity.
     *
     * @param id            unique ID
     * @param yearCeremony  year of the ceremony
     * @param category      award category
     * @param actorName     nominee name
     * @param winner        true if won, false otherwise
     * @param movie         associated movie
     * @param ceremony      number of the ceremony
     */
    public oscarAwards(Long id, int yearCeremony, String category, String actorName, boolean winner, movies movie, int ceremony) {
        this.id = id;
        this.yearCeremony = yearCeremony;
        this.category = category;
        this.actorName = actorName;
        this.winner = winner;
        this.movie = movie;
        this.ceremony = ceremony;
    }

    // Getters

    /**
     * Gets the ID of the award.
     *
     * @return award ID
     */
    public Long getAwardId() {
        return id;
    }

    /**
     * Gets the Oscar award category.
     *
     * @return category name
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the name of the nominee (person).
     *
     * @return nominee name
     */
    public String getPersonName() {
        return actorName;
    }

    /**
     * Gets the year the ceremony was held.
     *
     * @return ceremony year
     */
    public int getYearCeremony() {
        return yearCeremony;
    }

    /**
     * Checks whether the nominee won the Oscar.
     *
     * @return true if won, false otherwise
     */
    public boolean getWinner() {
        return winner;
    }

    /**
     * Gets the associated movie.
     *
     * @return movie entity
     */
    public movies getMovie() {
        return movie;
    }

    /**
     * Gets the number of the Oscar ceremony.
     *
     * @return ceremony number
     */
    public int getCeremony() {
        return ceremony;
    }

    // Setters

    /**
     * Sets the ID of the award.
     *
     * @param id award ID
     */
    public void setAwardId(Long id) {
        this.id = id;
    }

    /**
     * Sets the Oscar award category.
     *
     * @param category category name
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Sets the nominee name.
     *
     * @param personName nominee name
     */
    public void setPersonName(String personName) {
        this.actorName = personName;
    }

    /**
     * Sets the year of the ceremony.
     *
     * @param yearCeremony ceremony year
     */
    public void setYearCeremony(int yearCeremony) {
        this.yearCeremony = yearCeremony;
    }

    /**
     * Sets whether the nominee won the award.
     *
     * @param winner true if won, false otherwise
     */
    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    /**
     * Sets the associated movie.
     *
     * @param movie movie entity
     */
    public void setMovie(movies movie) {
        this.movie = movie;
    }

    /**
     * Sets the number of the Oscar ceremony.
     *
     * @param ceremony ceremony number
     */
    public void setCeremony(int ceremony) {
        this.ceremony = ceremony;
    }
}
