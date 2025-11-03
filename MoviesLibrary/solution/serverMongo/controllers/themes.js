const mongoose = require('mongoose');
const themes = require('../models/themes');

/**
 * Retrieves all theme documents associated with a specific movie ID.
 *
 * @function getAllByMovie
 * @param {Object} req - The HTTP request object. It should contain the movie ID
 *                       as a URL parameter (req.params.movie_id).
 * @param {Object} res - The HTTP response object used to return the results or an error.
 * @returns {void} Sends a JSON array of theme documents on success,
 *                 or a 500 status code with error details on failure.
 */
exports.getAllByMovie = function (req, res) {
    // Find all theme documents that match the given movie_id
    themes.find({movie_id: req.params.movie_id})
        .then(function (results) {
            // Send the results as JSON if the query is successful
            res.json(results);
        })
        .catch(function (error) {
            // Send a 500 status code and the error message if the query fails
            res.status(500).send(error);
        });
}
