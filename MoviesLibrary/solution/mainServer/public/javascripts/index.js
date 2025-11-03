/**
 * Script to handle movie search with debounce, pagination, and dynamic rendering using Axios.
 * Disables Enter key submit in the search input.
 */

let currentPage; // Tracks the current pagination page

/**
 * Called when the page finishes loading.
 * Initializes event listeners and sets the initial page number.
 */
window.onload = function () {
    init();
    currentPage = 0;
};

/**
 * Prevents rapid firing of a function by delaying its execution.
 * @param {Function} func - The function to debounce
 * @param {number} delay - Delay in milliseconds
 * @returns {Function} Debounced function
 */
function debounce(func, delay) {
    let timeout;
    return function (...args) {
        clearTimeout(timeout);
        timeout = setTimeout(() => func.apply(this, args), delay);
    };
}

/**
 * Performs the movie search request and renders the result in the film list container.
 * Handles both empty and filled query cases.
 */
const performSearch = async () => {
    const query = document.getElementById("searchInput").value.trim().toLowerCase();
    const category = document.getElementById("searchCategory").value;
    const filmList = document.getElementById("filmList");

    const params = {page: currentPage};
    if (query && category) {
        params.query = query;
        params.category = category;
    }

    try {
        const response = await axios.get('http://localhost:3000/search', {
            params,
            responseType: 'text' // Receive HTML as plain text
        });

        filmList.innerHTML = response.data;
        window.scrollTo({top: 300, behavior: "instant"});

    } catch (error) {
        if (error.response && error.response.status === 404) {
            // Custom 404 response from server (HTML page)
            filmList.innerHTML = error.response.data;
        } else {
            console.error('❌ Error during search:', error.message);
            filmList.innerHTML = '<p style="color:red;">An unexpected error occurred while loading results.</p>';
        }
        window.scrollTo({top: 100, behavior: "instant"});
    }
};

/**
 * Resets the page number when a new search input starts.
 */
const cleanPage = () => {
    currentPage = 0;
    document.getElementById("pageNum").innerHTML = currentPage + 1;
};

/**
 * Advances to the next page and fetches new results.
 */
const moveForward = async () => {
    currentPage += 1;
    document.getElementById("pageNum").innerHTML = currentPage + 1;
    await performSearch();
};

/**
 * Goes back one page (if not already on the first) and fetches new results.
 */
const moveBackward = async () => {
    if (currentPage > 0) {
        currentPage -= 1;
        document.getElementById("pageNum").innerHTML = currentPage + 1;
        await performSearch();
    }
};

/**
 * Sets up all initial event listeners and variables.
 */
function init() {
    const searchInput = document.getElementById("searchInput");

    // Trigger debounced search and page reset on input
    searchInput.addEventListener("input", debouncedSearch);
    searchInput.addEventListener("input", cleanPage);

    // Prevent Enter key from submitting the form
    searchInput.addEventListener("keydown", (e) => {
        if (e.key === "Enter") {
            e.preventDefault();
        }
    });

    // Pagination buttons
    document.getElementById("moveForward").addEventListener("click", moveForward);
    document.getElementById("moveBackward").addEventListener("click", moveBackward);

    // Initialize page to 0
    currentPage = 0;
    document.getElementById("pageNum").innerHTML = currentPage + 1;
}

// Debounced version of the search function
const debouncedSearch = debounce(performSearch, 300);
