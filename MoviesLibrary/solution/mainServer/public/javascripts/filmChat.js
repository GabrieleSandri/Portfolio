/**
 * Real-time chat functionality for a movie-specific room using Socket.io.
 * Handles username input, message sending, receiving, and system notifications.
 */
const socket = io(); // Connect to the Socket.io server

let username = '';
let movieId;
let room;

// DOM Elements
const movieElement = document.getElementById('filmChat');
const usernameForm = document.getElementById('usernameForm');
const usernameInput = document.getElementById('usernameInput');
const chatContainer = document.getElementById('chatContainer');
const chatBox = document.getElementById('chatBox');
const chatForm = document.getElementById('chatForm');
const chatInput = document.getElementById('chatInput');

/**
 * When the page is fully loaded, get the movie ID from the DOM
 * and use it to identify the chat room for this movie.
 */
document.addEventListener('DOMContentLoaded', () => {
    movieId = movieElement.dataset.movieId;
    room = movieId; // Use movie ID as the chat room name
});

/**
 * Handles the submission of the username form.
 * Hides the form, shows the chat box, and joins the Socket.io room.
 */
usernameForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const name = usernameInput.value.trim();
    if (!name) return;

    username = name;
    document.getElementById('usernameFormContainer').style.display = 'none';
    chatContainer.style.display = 'block';

    socket.emit('create or join conversation', room, username);
    appendSystemMessage(`✅ Hello ${username}! Welcome to the movie chat.`);
});

/**
 * Handles the chat form submission to send a new message.
 */
chatForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const message = chatInput.value.trim();
    if (!message) return;

    socket.emit('chat message', room, message, username);
    chatInput.value = '';
});

/**
 * Listens for chat messages from the server and displays them in the chat box.
 * @param {string} msg - The message content
 * @param {string} name - The name of the user who sent the message
 */
socket.on('chat message', (msg, name) => {
    if (msg === 'has left the conversation') {
        appendSystemMessage(`❌ ${name} ${msg}`);
        return;
    }

    const msgDiv = document.createElement('div');
    msgDiv.className = "mb-2";

    const sender = name === username ? "Me" : name;
    msgDiv.innerHTML = `<strong>${sender}:</strong> ${msg}`;
    chatBox.appendChild(msgDiv);
    chatBox.scrollTop = chatBox.scrollHeight;
});

/**
 * Listens for notifications when a new user joins the chat room.
 * @param {string} name - The name of the user who joined
 */
socket.on('create or join conversation', (name) => {
    if (name !== username) {
        appendSystemMessage(`👤 ${name} joined the chat.`);
    }
});

/**
 * Sends a "leave room" event to the server when the user is closing the page.
 */
window.addEventListener('beforeunload', () => {
    socket.emit('leave room', room, username);
});

/**
 * Displays a system message (such as join/leave notifications).
 * @param {string} message - The system message to show
 */
function appendSystemMessage(message) {
    const msgDiv = document.createElement('div');
    msgDiv.className = "text-muted fst-italic small mb-2";
    msgDiv.textContent = message;
    chatBox.appendChild(msgDiv);
    chatBox.scrollTop = chatBox.scrollHeight;
}
