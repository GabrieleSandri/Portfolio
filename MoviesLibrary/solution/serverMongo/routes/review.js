var express = require('express');
var router = express.Router();
var review = require('../controllers/reviews');

/**
 * Route to get all reviews by movie ID.
 *
 * HTTP GET /:movie_id
 * Invokes the reviews controller's getAllByMovie method,
 * passing the request and response objects.
 */
router.get('/:movie_id', function (req, res, next) {
    review.getAllByMovie(req, res);
});

module.exports = router;
