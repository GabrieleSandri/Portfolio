const mongoose = require('mongoose');
const schema = mongoose.Schema;

/**
 * Mongoose schema for the 'posters' collection.
 * Each document represents a poster associated with a specific movie.
 *
 * Fields:
 * - movie_id: Numeric ID of the movie the poster belongs to (required).
 * - link: URL or path to the poster image (required).
 */
const posters = new schema({
    movie_id: {type: Number, required: true},  // ID of the associated movie
    link: {type: String, required: true}       // URL or local path to the poster image
});

// Enable getter functions when converting documents to plain JavaScript objects
posters.set("toObject", {getters: true});

// Export the model to be used in other parts of the application
module.exports = mongoose.model("posters", posters);
