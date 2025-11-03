const mongoose = require('mongoose');
const schema = mongoose.Schema;

/**
 * Mongoose schema for the 'studios' collection.
 * Each document represents a studio involved in a specific movie.
 *
 * Fields:
 * - movie_id: Numeric ID of the associated movie (required).
 * - studio: Name of the studio (required).
 */
const studios = new schema({
    movie_id: {type: Number, required: true},  // ID of the associated movie
    studio: {type: String, required: true}     // Name of the studio
});

// Enable getter functions when converting documents to plain JavaScript objects
studios.set("toObject", {getters: true});

// Export the model to be used elsewhere in the application
module.exports = mongoose.model("studios", studios);
