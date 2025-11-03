var express = require('express');
var router = express.Router();
var releases = require('../controllers/releases');

/**
 * Route to get all releases by movie ID.
 *
 * HTTP GET /:movie_id
 * Calls the releases controller's getAllByMovie function,
 * passing the request and response objects.
 */
router.get('/:movie_id', function (req, res, next) {
    releases.getAllByMovie(req, res);
});

module.exports = router;
