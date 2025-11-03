var express = require('express');
var router = express.Router();
var themes = require('../controllers/themes');

/**
 * Route to get all themes by movie ID.
 *
 * HTTP GET /:movie_id
 * Calls the themes controller's getAllByMovie function,
 * passing the request and response objects.
 */
router.get('/:movie_id', function (req, res, next) {
    themes.getAllByMovie(req, res);
});

module.exports = router;
