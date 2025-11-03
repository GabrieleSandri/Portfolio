const mongoose = require('mongoose');
const schema = mongoose.Schema;

/**
 * Mongoose schema for the 'releases' collection.
 * Each document represents a movie release in a specific country with additional metadata.
 *
 * Fields:
 * - movie_id: Numeric ID of the movie being released (required).
 * - country_name: Name of the country where the movie was released (required).
 * - release_date: Date of the movie's release in that country (required).
 * - type: Type of release (e.g., "theatrical", "digital", "TV") (required).
 * - rating: Age rating or classification for the release in that country (e.g., "PG-13", "R") (required).
 */
const releases = new schema({
    movie_id: {type: Number, required: true},          // ID of the associated movie
    country_name: {type: String, required: true},      // Country where the movie was released
    release_date: {type: Date, required: true},        // Date of release
    type: {type: String, required: true},              // Type of release (e.g., theatrical, digital)
    rating: {type: String, required: true},            // Country-specific age rating
});

// Enable getter functions when converting documents to plain JavaScript objects
releases.set("toObject", {getters: true});

// Export the model to be used elsewhere in the application
module.exports = mongoose.model("releases", releases);
