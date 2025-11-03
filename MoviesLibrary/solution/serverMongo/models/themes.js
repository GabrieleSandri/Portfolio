const mongoose = require('mongoose');
const schema = mongoose.Schema;

/**
 * Mongoose schema for the 'themes' collection.
 * Each document represents a theme associated with a specific movie.
 *
 * Fields:
 * - movie_id: Numeric ID of the associated movie (required).
 * - theme: Name or description of the theme (required).
 */
const themes = new schema({
    movie_id: {type: Number, required: true},  // ID of the associated movie
    theme: {type: String, required: true}      // Name or description of the theme
});

// Enable getter functions when converting documents to plain JavaScript objects
themes.set("toObject", {getters: true});

// Export the model to be used elsewhere in the application
module.exports = mongoose.model("themes", themes);
