/**
 * Script to handle the review carousel and lazy loading of additional reviews
 * on a movie details page.
 */
//Dom Element and variable
const prevBtn = document.querySelector('.carousel-btn.prev');
const nextBtn = document.querySelector('.carousel-btn.next');
const scrollContainer = document.querySelector('.reviews-list-wrapper');
const reviewsContainer = document.getElementById('reviews-list');
const movieElement = document.getElementById('filmdetails');
const movieId = movieElement?.dataset.movieId;

const scrollAmount = 340;
let currentPage = 2; // Start loading from page 2 since page 1 is preloaded

document.addEventListener('DOMContentLoaded', () => {
    console.log('✅ Page loaded!');
    initializeCarousel();
    attachLoadMoreHandler();
});

/**
 * Attaches click events to the carousel navigation buttons.
 * Allows smooth scrolling left and right through the review cards.
 */
function initializeCarousel() {
    prevBtn?.addEventListener('click', () => {
        scrollContainer.scrollBy({left: -scrollAmount, behavior: 'smooth'});
    });

    nextBtn?.addEventListener('click', () => {
        scrollContainer.scrollBy({left: scrollAmount, behavior: 'smooth'});
    });

    scrollContainer?.addEventListener('scroll', updateCarouselButtons);
    updateCarouselButtons(); // Initial state
}

/**
 * Enables or disables the carousel buttons based on scroll position.
 */
function updateCarouselButtons() {
    if (!scrollContainer) return;

    prevBtn.disabled = scrollContainer.scrollLeft <= 0;
    nextBtn.disabled = scrollContainer.scrollLeft + scrollContainer.clientWidth >= scrollContainer.scrollWidth - 1;
}

/**
 * Finds the "Show More" button and attaches a click event to load more reviews.
 */
function attachLoadMoreHandler() {
    const loadMoreBtn = document.getElementById('showMore');
    if (loadMoreBtn) {
        console.log('🔍 Show More button found.');
        loadMoreBtn.addEventListener('click', loadMoreReviews);
    }
}

/**
 * Makes a request to fetch additional reviews for the current movie.
 * Appends the new reviews to the container and reattaches the "Show More" handler.
 */
function loadMoreReviews() {
    if (!movieId) {
        console.error('❌ Movie ID not found.');
        return;
    }

    axios.get(`http://localhost:3000/reviews/${movieId}?page=${currentPage}`)
        .then(res => {
            // Remove the old "Show More" button if it exists
            const oldBtn = document.getElementById('showMore');
            oldBtn?.remove();

            // Append new reviews
            reviewsContainer.insertAdjacentHTML('beforeend', res.data);

            // Reattach handler for new "Show More" button, if present
            attachLoadMoreHandler();

            // Move to next page for future loads
            currentPage++;
        })
        .catch(err => {
            console.error('❌ Failed to load more reviews:', err);
        });
}
