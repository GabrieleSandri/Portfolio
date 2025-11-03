var express = require('express');
var router = express.Router();
var languages = require('../controllers/languages');

/**
 * Route to get all languages by movie ID.
 *
 * HTTP GET /:movie_id
 * Calls the languages controller's getAllByMovie function,
 * passing the request and response objects.
 */
router.get('/:movie_id', function (req, res, next) {
    languages.getAllByMovie(req, res);
});

module.exports = router;
