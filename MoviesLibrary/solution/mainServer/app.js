/**
 * Main application setup for the Express.js web server.
 * This file initializes middlewares, view engine, routes, and error handling.
 */

const createError = require('http-errors');
const express = require('express');
const path = require('path');
const cookieParser = require('cookie-parser');
const logger = require('morgan');
const swaggerUi = require('swagger-ui-express');
const openApiDocumentation = require('./openApiDocumentation');

// Route handlers
const indexRouter = require('./routes/index');

const app = express();

/**
 * ========== View Engine Setup (Handlebars) ==========
 * - Uses Handlebars (hbs) as the templating engine
 * - Layouts and partials are placed in the views folder
 */
const {engine} = require("express-handlebars");

app.engine('hbs', engine({
    extname: '.hbs',
    defaultLayout: 'layout',
    layoutsDir: path.join(__dirname, 'views/layouts'),
    partialDir: path.join(__dirname, 'views/partials'),
}));

app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'hbs');

/**
 * ========== Middleware Setup ==========
 */

// Logs HTTP requests to the console (e.g., GET /home 200)
app.use(logger('dev'));

// Parses incoming JSON and URL-encoded payloads
app.use(express.json());
app.use(express.urlencoded({extended: false}));

// Parses cookies attached to the client request object
app.use(cookieParser());

// Serves static files (CSS, JS, images) from /public directory
app.use(express.static(path.join(__dirname, 'public')));

/**
 * ========== Routes ==========
 */

// Root route handler
app.use('/', indexRouter);

// Swagger UI documentation route
app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(openApiDocumentation));

/**
 * ========== 404 Handler ==========
 * If no route matches, create a 404 error and pass it to the error handler.
 */
app.use((req, res, next) => {
    next(createError(404));
});

/**
 * ========== General Error Handler ==========
 * Renders an error page with helpful message.
 */
app.use((err, req, res, next) => {
    // Provide error stack trace only in development mode
    res.locals.message = err.message;
    res.locals.error = req.app.get('env') === 'development' ? err : {};

    // Respond with status code and render error page
    res.status(err.status || 500);
    res.render('pages/error');
});

// Export the configured Express app
module.exports = app;
