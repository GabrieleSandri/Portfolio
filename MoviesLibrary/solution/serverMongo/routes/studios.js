var express = require('express');
var router = express.Router();
var studios = require('../controllers/studios');

/**
 * Route to get all studios by movie ID.
 *
 * HTTP GET /:movie_id
 * Calls the studios controller's getAllByMovie function,
 * passing the request and response objects.
 */
router.get('/:movie_id', function (req, res, next) {
    studios.getAllByMovie(req, res);
});

module.exports = router;
