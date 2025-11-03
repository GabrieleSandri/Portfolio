var express = require('express');
var router = express.Router();
const axios = require("axios");

/* ============================================================================
   SECTION: HOME PAGE ROUTES
   Description: Handles all routes related to the home page and its content
============================================================================ */

/**
 * GET /
 * Loads the homepage with a list of movies, each enriched with a poster image.
 */
router.get('/', async (req, res) => {
    try {
        const films = await fetchMovies(0);
        const enrichedFilms = await enrichMoviesWithImages(films);

        if (enrichedFilms.length === 0) {
            return res.status(404).render('pages/error', {
                layout: false,
                message: 'No content available',
                status: 404
            });
        }

        res.render('pages/index', {
            films: enrichedFilms
        });

    } catch (err) {
        console.error('❌ Error while loading homepage:', err.message);
        res.status(500).render('pages/error', {
            layout: false,
            message: 'An error occurred while retrieving the movie details. Please try again later.',
            status: 500,
        });
    }
});

/**
 * Fetches a paginated list of movies from the Spring Boot backend.
 *
 * @param {number} page - Page number to fetch
 * @returns {Promise<Array>} - List of movies from backend
 */
async function fetchMovies(page) {
    const response = await axios.get('http://localhost:8082/movies', {
        params: {page},
    });
    return response.data.content || [];
}

/* --------------------------------------------------------------------------
   SUBSECTION: Search route and display posters
-------------------------------------------------------------------------- */
/**
 * GET /search
 * Searches for movies based on query parameters (category, query, page).
 * Return the movielist
 */
router.get('/search', async function (req, res) {
    const {category, query, page} = req.query;

    try {
        let movieList = [];

        if (!query || !category) {
            movieList = await fetchMovies(page);
        } else {
            movieList = await fetchSpecificMovies(category, query, page);
        }

        const enrichedFilms = await enrichMoviesWithImages(movieList);

        if (enrichedFilms.length === 0) {
            return res.status(404).render('pages/error', {
                layout: false,
                message: 'No content available',
                status: 404
            });
        }

        res.render('partials/filmList', {
            layout: false,
            films: enrichedFilms,
        });

    } catch (err) {
        console.error('❌ Error retrieving movie list:', err.message);
        res.status(500).render('pages/error', {
            layout: false,
            message: 'An error occurred while retrieving the movie details. Please try again later.',
            status: 500
        });
    }
});

/**
 * Fetches movies by actor name, genre, or movie name from the Spring Boot backend.
 *
 * @param {string} category - The filter category ('actor', 'category', 'name')
 * @param {string} query - The value to search for
 * @param {number} page - The page number for pagination
 * @returns {Promise<Array>} - Filtered movie list
 */
async function fetchSpecificMovies(category, query, page) {
    let url = 'http://localhost:8082/movies';
    let params = {page};

    switch (category) {
        case 'actor':
            url += '/actorsMovies';
            params.actorName = query;
            break;
        case 'category':
            url += '/genresMovies';
            params.genreName = query;
            break;
        case 'name':
            url += '/name';
            params.movieName = query;
            break;
        default:
            break;
    }

    const response = await axios.get(url, {params});
    return response.data.content || [];
}

/**
 * Adds image URLs from MongoDB to each movie object.
 *
 * @param {Array} movieList - Array of movie objects
 * @returns {Promise<Array>} - List of enriched movie objects with image URLs
 */
async function enrichMoviesWithImages(movieList) {
    return Promise.all(
        movieList.map(async (film) => {
            try {
                const mongoResponse = await axios.get(`http://localhost:3001/posters/${film.id}`);
                return {
                    ...film,
                    imageUrl: mongoResponse.data.link || null,
                };
            } catch (err) {
                console.error(`❌ Error fetching image for movie ${film.id}:`, err.message);
                return {
                    ...film,
                    imageUrl: null,
                };
            }
        })
    );
}

/* --------------------------------------------------------------------------
   SUBSECTION: Route for movie details
-------------------------------------------------------------------------- */
/**
 * GET /movie/:id
 * Displays detailed info about a specific movie.
 */

router.get('/movie/:id', async (req, res) => {
    const filmId = req.params.id;

    try {
        const rawData = await fetchMovieResources(filmId);
        const movie = buildMovieObject(rawData);

        if (!movie) {
            return res.status(404).render('pages/error', {
                message: 'No content available',
                status: 404
            });
        }

        res.render('pages/filmDetails', {...movie});

    } catch (err) {
        console.error(`❌ Failed to fetch movie details for ID ${filmId}:`, err.message);
        res.status(500).render('pages/error', {
            message: 'An error occurred while retrieving the movie details. Please try again later.',
            status: 500
        });
    }
});

/**
 * GET /reviews/:movieId?page=X
 * Loads additional reviews for a movie, supports pagination.
 */

router.get('/reviews/:id', async function (req, res) {
    const page = req.query.page;
    const movieId = req.params.id;

    try {
        const mongoResponse = await axios.get(`http://localhost:3001/reviews/${movieId}?page=${page}`);
        const reviews = (mongoResponse.data || []).map(review => {
            if (!review || !review.review_date) {
                return review; // oppure puoi restituire un oggetto vuoto o gestire diversamente
            }
            return {
                ...review,
                review_date: review.review_date.slice(0, 10)
            };
        });
        console.log(reviews)
        res.render('partials/reviewsList', {
            layout: false,
            reviews: reviews
        });

    } catch (error) {
        console.error('❌ Failed to fetch movie reviews', error);
        res.status(500).render('pages/error', {
            message: 'An error occurred while retrieving the reviews of this movie',
            status: 500
        });
    }
});

/**
 * Fetches all movie-related data from different microservices in parallel.
 *
 * @param {string} filmId - The unique ID of the movie
 * @returns {Promise<object>} - Aggregated movie data
 */

async function fetchMovieResources(filmId) {
    const endpoints = [
        axios.get(`http://localhost:8082/movies/id?id=${filmId}`),
        axios.get(`http://localhost:8082/actors/movie?movie_id=${filmId}`),
        axios.get(`http://localhost:8082/countries/movie?movie_id=${filmId}`),
        axios.get(`http://localhost:8082/crews/movie?movie_id=${filmId}`),
        axios.get(`http://localhost:8082/oscarAwards/movie?movie_id=${filmId}`),
        axios.get(`http://localhost:3001/studios/${filmId}`),
        axios.get(`http://localhost:3001/posters/${filmId}`),
        axios.get(`http://localhost:3001/languages/${filmId}`),
        axios.get(`http://localhost:3001/reviews/${filmId}`),
        axios.get(`http://localhost:3001/themes/${filmId}`)
    ];

    const [
        detailsRes, actorsRes, countriesRes, crewsRes, oscarRes,
        studiosRes, posterRes, languagesRes, reviewsRes, themesRes
    ] = await Promise.all(endpoints);

    return {
        details: detailsRes.data,
        actors: actorsRes.data,
        countries: countriesRes.data,
        crews: crewsRes.data,
        oscars: oscarRes.data,
        studios: studiosRes.data,
        poster: posterRes.data,
        languages: languagesRes.data,
        reviews: reviewsRes.data,
        themes: themesRes.data
    };
}

/**
 * Combines and limits movie data for rendering.
 *
 * @param {object} rawData - Raw data fetched from services
 * @param {number} limit - Max number of actors/crews to show
 * @returns {object} - Formatted movie object
 */

function buildMovieObject(rawData, limit = 10) {
    let moviePartials = {
        ...rawData.details,

        languages: rawData.languages.map(lang => ({
            type: lang.type,
            language: lang.language
        })),
        themes: rawData.themes.map(t => ({theme: t.theme})),
        studios: rawData.studios.map(s => ({studio: s.studio})),
        country: rawData.countries.map(c => ({name: c.name})),
        reviews: (rawData.reviews || []).map(review => ({
            ...review,
            review_date: review.review_date.slice(0,10)
        })),
        actors: rawData.actors.slice(0, limit).map(actor => ({
            name: actor.name,
            role: actor.role
        })),
        crews: rawData.crews.slice(0, limit).map(crew => ({
            name: crew.name,
            role: crew.role
        })),
        oscars: rawData.oscars.map(oscars => ({
            personName: oscars.personName,
            category: oscars.category,
            winner: oscars.winner,
        }))
    };

    try {
        return {
            ...moviePartials,
            imageUrl: rawData.poster.link || null,
        };
    } catch (err) {
        console.error(`❌ Error fetching image for movie ${rawData.details.movie_id}:`, err.message);
        return {
            ...moviePartials,
            imageUrl: null,
        };
    }
}

/* --------------------------------------------------------------------------
   SUBSECTION: movie chat opening and manage
-------------------------------------------------------------------------- */
/**
 * GET /movie/:id/chat
 * Opens the chat page for a specific movie.
 */

router.get('/movie/:id/chat', async function (req, res) {
    try {
        const filmId = Number(req.params.id);
        if (isNaN(filmId)) {
            return res.status(404).render('pages/error', {
                message: 'An error occurred while retrieving the chat of this movie',
                status: 404
            });
        }

        const response = await axios.get(`http://localhost:8082/movies/id?id=${filmId}`);
        const movie = response.data;
        res.render('pages/filmChat', {...movie});

    } catch (error) {
        console.error(`❌ Failed to fetch movie chat`, error);
        res.status(500).render('pages/error', {
            message: 'An error occurred while retrieving the chat of this movie',
            status: 500
        });
    }
});

module.exports = router;
