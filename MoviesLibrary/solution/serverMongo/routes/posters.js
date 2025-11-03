var express = require('express');
var router = express.Router();
var posters = require('../controllers/posters');

/**
 * Route to get the poster by movie ID.
 *
 * HTTP GET /:movie_id
 * Calls the posters controller's getByMovie function,
 * passing the request and response objects.
 */
router.get('/:movie_id', function (req, res, next) {
    posters.getByMovie(req, res);
});

module.exports = router;
