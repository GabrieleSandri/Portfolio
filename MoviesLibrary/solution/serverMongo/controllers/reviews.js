const mongoose = require('mongoose');
const review = require('../models/reviews');

/**
 * Retrieves a paginated list of review documents associated with a specific movie ID.
 *
 * @function getAllByMovie
 * @param {Object} req - The HTTP request object. It should contain:
 *   - movie_id as a URL parameter (req.params.movie_id)
 *   - optional query parameters: `page` (page number) and `limit` (items per page)
 * @param {Object} res - The HTTP response object used to send back the results or an error.
 * @returns {void} Sends a JSON array of review documents for the specified page and limit,
 *                 or a 500 status code with error details on failure.
 */
exports.getAllByMovie = function (req, res) {
    // Parse the page number from the query string, default to 1
    const page = parseInt(req.query.page) || 1;

    // Parse the number of items per page from the query string, default to 10
    const limit = parseInt(req.query.limit) || 10;

    // Calculate how many documents to skip
    const skip = (page - 1) * limit;

    // Find reviews for the given movie_id, apply pagination
    review.find({movie_id: req.params.movie_id})
        .skip(skip)       // Skip documents for previous pages
        .limit(limit)     // Limit the number of documents returned
        .then(function (results) {
            // Send the results as JSON if the query is successful
            res.json(results);
        })
        .catch(function (error) {
            // Send a 500 status code and the error message if the query fails
            res.status(500).send(error);
        });
}
