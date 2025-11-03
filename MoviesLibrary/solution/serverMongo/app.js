var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
const swaggerUi = require('swagger-ui-express');
const openApiDocumentation = require('./openApiDocumentation');

var reviewRouter = require('./routes/review');
var releasesRouter = require('./routes/releases');
var postersRouter = require('./routes/posters');
var languagesRouter = require('./routes/languages');
var studiosRouter = require('./routes/studios');
var themesRouter = require('./routes/themes');

var app = express();

/**
 * Middleware setup
 */
// Logger middleware for HTTP request logging in 'dev' format
app.use(logger('dev'));

// Middleware to parse incoming JSON requests
app.use(express.json());

// Middleware to parse URL-encoded data (form submissions)
app.use(express.urlencoded({ extended: false }));

// Middleware to parse cookies from the HTTP request
app.use(cookieParser());

/**
 * Route handlers for different API endpoints
 */
// Routes related to movie reviews
app.use('/reviews', reviewRouter);

// Routes related to movie releases
app.use('/releases', releasesRouter);

// Routes related to movie posters
app.use('/posters', postersRouter);

// Routes related to movie languages
app.use('/languages', languagesRouter);

// Routes related to movie studios
app.use('/studios', studiosRouter);

// Routes related to movie themes
app.use('/themes', themesRouter);

/**
 * Swagger UI setup for API documentation
 *
 * Serves the OpenAPI documentation UI at the '/api-docs' endpoint.
 */
app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(openApiDocumentation));

module.exports = app;
