const mongoose = require('mongoose');
const schema = mongoose.Schema;

/**
 * Mongoose schema for the 'languages' collection.
 * Each document represents a language entry related to a specific movie.
 *
 * Fields:
 * - movie_id: Numeric ID that identifies the associated movie (required).
 * - type: Type/category of the language (e.g., "spoken", "subtitle") (required).
 * - language: Language code or name (e.g., "English", "fr", "es") (required).
 */
const languages = new schema({
    movie_id: {type: Number, required: true},   // ID of the associated movie
    type: {type: String, required: true},       // Type of the language (spoken, subtitle, etc.)
    language: {type: String, required: true}    // Language name or code
});

// Enable getter functions when converting documents to plain JavaScript objects
languages.set("toObject", {getters: true});

// Export the model to be used in other parts of the application
module.exports = mongoose.model("languages", languages);
