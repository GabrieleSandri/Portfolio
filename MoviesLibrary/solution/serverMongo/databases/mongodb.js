const mongoose = require("mongoose");

// MongoDB connection URL (local database named 'movies_db')
const mongoDB = 'mongodb://localhost:27017/movies_db';

// Set Mongoose to use native JavaScript Promises
mongoose.Promise = global.Promise;

/**
 * Establishes a connection to the MongoDB database using Mongoose.
 *
 * Uses the following options:
 * - useNewUrlParser: Allows the use of the new MongoDB connection string parser.
 * - useUnifiedTopology: Opts in to using the MongoDB driver's new connection management engine.
 * - checkServerIdentity: Disabled to bypass hostname verification (not recommended in production).
 *
 * Logs a success message if the connection is established,
 * or logs the error details if the connection fails.
 */
const connection = mongoose.connect(mongoDB, {
    useNewUrlParser: true,
    useUnifiedTopology: true,
    checkServerIdentity: false,
})
    .then(() => {
        // Log message if connection is successful
        console.log('Connection to MongoDB worked!');
    })
    .catch((error) => {
        // Log error message if connection fails
        console.log('Connection to MongoDB did not work! ' + JSON.stringify(error));
    });
;