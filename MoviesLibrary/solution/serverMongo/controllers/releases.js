const mongoose = require('mongoose');
const releases = require('../models/releases');

/**
 * Retrieves all release documents associated with a specific movie ID.
 *
 * @function getAllByMovie
 * @param {Object} req - The HTTP request object. It should contain the movie ID as a URL parameter (req.params.movie_id).
 * @param {Object} res - The HTTP response object used to send back the results or an error.
 * @returns {void} Sends a JSON array of release documents on success, or a 500 status code with error details on failure.
 */
exports.getAllByMovie = function (req, res) {
    // Find all release documents that match the given movie_id
    releases.find({movie_id: req.params.movie_id})
        .then(function (results) {
            // Send the results as JSON if the query is successful
            res.json(results);
        })
        .catch(function (error) {
            // Send a 500 status code and the error message if the query fails
            res.status(500).send(error);
        });
}
