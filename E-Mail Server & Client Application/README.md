# Mail Server and Client Application

This project implements a mail server and client system where the server handles user login, email reception, and transmission to and from clients. The server and client communicate over sockets, with Java's Object Streams facilitating the transmission of user and email data. The system is designed to handle multiple concurrent connections through a thread pool.

## Technologies Used

- **Java**: The main programming language for both client and server applications.
- **JavaFX**: Used for creating the graphical user interface (GUI) for the client application.
- **Socket Programming**: For communication between the client and server.
- **Multi-threading**: Managed through an executor service for concurrent client handling.
- **Object Streams**: For the serialization and deserialization of user and email objects.
- **ReentrantReadWriteLock**: For synchronizing access to resources like user data and email inboxes.
- **IntelliJ IDEA**: The development environment used for writing and testing the project.

## Key Features

### Server-Side Features
- **Multiple Client Support**: The server can handle multiple client connections simultaneously using a thread pool.
- **User Authentication**: Clients can log in with their credentials (email), and the server validates their existence and retrieves their inbox.
- **Email Reception and Sending**: The server allows clients to send emails to multiple recipients, check for new messages, and delete emails.
- **Synchronization**: The server uses a `ReadWriteLock` to synchronize access to user data and email inboxes, ensuring safe concurrent access.

### Client-Side Features
- **User Interface**: The client application provides a simple graphical interface to interact with the server, including sending, receiving, and deleting emails.
- **Network Connectivity**: The client can connect to the server over a specified IP address and port. It can be configured to run on a local network by modifying the IP address.


## Implementation Details

- **Server**: The server is implemented using a `ServerSocket` that listens for incoming client connections. It use a new thread of the pool for each client to handle communication, using the `Communication` class. The server supports basic email operations such as login, inbox retrieval, email sending, and email deletion, forwording, reply all.
  
- **Client**: The client application uses a GUI built with JavaFX, allowing users to interact with the server. The client can connect to the server by specifying the server's IP address and port. The application uses `Socket` programming to communicate with the server over the network.

- **Persistence**: User data (such as emails and inboxes) is stored in files, which are serialized and deserialized using Java's `ObjectInputStream` and `ObjectOutputStream`. Each user’s inbox is stored as a separate file.

## Offline Handling

If the server is unavailable while the client is connected:
- The client will display a notification indicating that the server is offline.
- Any unsent emails or operations will be saved locally in the client's session.
- Once the server is back online, the client will automatically send the pending data.
- A warning will be displayed if the client is closed while offline, informing the user that any unsent data will be lost.

## Requirements

- **Java 11 or higher**: Required to run both the client and server applications.
- **IntelliJ IDEA**: Recommended IDE for development and testing.

## Author
-Sandri Gabriele
-Sandri Mattia
