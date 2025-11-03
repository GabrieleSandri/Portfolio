const mongoose = require('mongoose');
const schema = mongoose.Schema;

/**
 * Mongoose schema for the 'review' collection.
 * Each document represents a review of a movie by a critic.
 *
 * Fields:
 * - movie_id: Numeric ID of the associated movie (required).
 * - critic_name: Name of the critic who wrote the review (required).
 * - publisher_name: Name of the publication or platform that published the review (required).
 * - review_date: Date when the review was published (required).
 * - review_score: Numeric score assigned by the critic (required).
 * - review_content: Full textual content of the review (required).
 * - top_critic: Boolean flag indicating whether the critic is a "top critic" (required).
 * - review_type: Type of the review (e.g., "fresh", "rotten") (required).
 * - rotten_tomatoes_link: Optional URL linking to the review on Rotten Tomatoes (optional).
 */
const review = new schema({
    movie_id: {type: Number, required: true},           // Associated movie ID
    critic_name: {type: String, required: true},        // Name of the critic
    publisher_name: {type: String, required: true},     // Name of the publisher/platform
    review_date: {type: Date, required: true},          // Date of the review
    review_score: {type: Number, required: true},       // Numeric score given by the critic
    review_content: {type: String, required: true},     // Text of the review
    top_critic: {type: Boolean, required: true},        // True if the critic is a "top critic"
    review_type: {type: String, required: true},        // Category/type of the review
    rotten_tomatoes_link: {type: String, required: false} // Optional Rotten Tomatoes URL
});

// Enable getter functions when converting documents to plain JavaScript objects
review.set("toObject", {getters: true});

// Export the model to be used elsewhere in the app
module.exports = mongoose.model("review", review);
