const mongoose = require('mongoose');
const posters = require('../models/posters');

/**
 * Retrieves the poster document associated with a specific movie ID.
 *
 * @function getByMovie
 * @param {Object} req - The HTTP request object. It should contain the movie ID as a URL parameter (req.params.movie_id).
 * @param {Object} res - The HTTP response object used to return the result or an error.
 * @returns {void} Sends a JSON object containing the poster data if found, or a 500 status code with error details on failure.
 */
exports.getByMovie = function (req, res) {
    // Find a single poster document that matches the given movie_id
    posters.findOne({movie_id: req.params.movie_id})
        .then(function (results) {
            // Send the result as JSON if the query is successful
            res.json(results);
        })
        .catch(function (error) {
            // Send a 500 status code and the error message if the query fails
            res.status(500).send(error);
        });
}
