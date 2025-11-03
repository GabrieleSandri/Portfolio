const mongoose = require('mongoose');
const languages = require('../models/languages');

/**
 * Retrieves all language documents associated with a specific movie ID.
 *
 * @function getAllByMovie
 * @param {Object} req - The HTTP request object, containing the movie ID as a URL parameter (req.params.movie_id).
 * @param {Object} res - The HTTP response object used to send back the results or an error.
 * @returns {void} Sends a JSON array of language documents on success, or a 500 status code with error details on failure.
 */
exports.getAllByMovie = function (req, res) {
    // Find all language documents that match the given movie_id
    languages.find({movie_id: req.params.movie_id})
        .then(function (results) {
            // Send the results as JSON if the query is successful
            res.json(results);
        })
        .catch(function (error) {
            // Send a 500 status code and the error message if the query fails
            res.status(500).send(error);
        });
}
