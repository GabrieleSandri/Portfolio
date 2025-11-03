# TeamWork Projects
**Acquired skills:** TeamWork, algorithms and Data Structures, algorithms complexity, programming language compiler, DFA, sorting algorithms, Prim's algorithm, Java, C, memoization, OOP, dynamic programming, Unit testing, web development (HTML, CSS, Bootstrap, JavaScript, PHP, PHPMailer), SQL, Design of DataBase, Node.js, Mongo DB, Socket.Io, Express.js, Java Spring.

## Team:
   - **[Gabriele Sandri](https://github.com/GabrieleSandri)**
   - **[Sandri Mattia](https://github.com/S-mattia)**

## 1. Algorithms and Data Structures
*(Project for a University exam)*
This project involves the implementation of various algorithms and data structures in Java and C. It includes sorting algorithms, data structures like priority queues and graphs, and dynamic programming techniques. 

### Deliverables:
   - Implementation of Marge Sort and Quick Sort algorithms, optimized for datasets up to 20 million records. `(EX1)`
     **Video Demo:** [20 million sorting DEMO](https://youtu.be/1d4FPnIMZi8)
   - Dynamic Programming Edit distance calculation with memoization. `(EX2)`
      **Video Demo:** [Text cheker DEMO](https://youtu.be/PcQRNddp0pI)
   - Implementation od data Structures Including priority queues and graph structures. `(EX3_4)`
   - Prim's algorithm using a priority queue and graph. `(EX3_4)`
     **Video Demo:** [Prim's Algorithm DEMO](https://youtu.be/NTorSjNKTdY)
   

## 2. Custom Programming Language
*(Project for a University exam)*
This project involves the creation of a basic programming language for the JVM, including a lexer and parser, as well as a simple DFA (Deterministic Finite Automaton) project. The lexer converts the source code into tokens, while the parser processes these tokens to generate Jasmin bytecode instructions.

### Deliverables:
   - Some exercises on DFA, Parser, Lexer. `(SEZ_1 SEZ_3 SEZ_4 SEZ_FC)`
   - A lexer that tokenizes the input program. `(SEZ_2)`
   - A translator that translate the token producted by lexer into instructions jasmine. `(SEZ_5)`
   - support classes. `(SEZ_5)`

**Video Demo:** [Custom Programming Language DEMO](https://youtu.be/FXhreJDFMgU)

## 3. Database design for a food delivery application:
*(Project for a University exam)*
This project involved designing a database for a food delivery application, from the ER diagram to the final SQL code. The process included analyzing requirements, creating an ER diagram to model entities like customers, orders, and restaurants, and normalizing the schema to 3NF. The SQL script provides the PostgreSQL code to create the database structure, define relationships, and insert sample data.

### Deliverables:

   - A `PDF` report detailing the design, ER diagram, and normalization steps.
   - An `SQL` script to create the PostgreSQL database.

## 4. Website for Accommodation Facility
A web development project for an accommodation facility, featuring front-end technologies (HTML, CSS, JavaScript) and back-end functionality in PHP.

[`https://www.domuslanghe.it`](https://www.domuslanghe.it)

## **5. Chain Reaction Simulation in Unix**  
*(Project for a University exam)*
This project simulates a chain reaction using multiple processes in a Unix environment. The simulation involves inter-process communication (IPC) and synchronization mechanisms to manage and coordinate different roles, including master, atom, activator, inhibitor, and feeder processes. The master process handles statistics, while other processes simulate energy production, inhibition, and waste generation. The project demonstrates Unix concepts such as process control, shared memory, message queues, and signal handling.  

**Deliverables:**  
- Implementation of inter-process communication using message queues and shared memory.  
- Process synchronization with semaphores to ensure consistent operations.  
- Signal handling (SIGUSR1 and SIGUSR2) for process activation and inhibition.  
- Configuration file support for runtime parameters using the INI format.  
- Logging system to track simulation events and statistics in real-time.

 **Video Demo:** [Chain Reaction Simulation in Unix DEMO](https://youtu.be/xmnROg0XTN0)


## 6. E-mail Server & Client Application
*(Project for a University exam)* This project involves the creation of a client-server mail application built with **Java**, **JavaFX**, and **Maven**. The server handles incoming requests from multiple clients, processes them, and manages user email data stored in files. The client application, with a graphical user interface, allows users to send and receive emails. Communication between the server and client is done through object streams. The client is configured to work on a local network, where the server IP can be modified in the client controller. The application is built and managed with **Maven**, and development was done using **IntelliJ IDEA** as IDE.

(Files were used to store data instead of a database due to a specific requirement of the project.)

**Deliverables:**

- **Server** (`src/com/example/server/support/server.java`):  
  A multithreaded server that listens on a given port, handles client connections, processes email requests, and uses a `ReadWriteLock` to synchronize access to email files. It handles operations like sending, receiving, and deleting emails.
  
- **Client** (`src/com/example/server/Communication.java`):  
  A client application that communicates with the server to send and receive emails. It manages the user interface with **JavaFX** and allows the user to view their inbox, send new emails, and handle disconnections from the server. If the server goes offline, the client alerts the user and ensures that any pending actions are lost if not completed.
  
**Video Demo:** [E-mail Server & Client Application DEMO](https://youtu.be/-gEK6whg0ww)

## 7. Movies Library Project  
*(Project for a University exam)* 
This project implements a **Movies Library Web Application** with a responsive frontend and two backend architectures (MongoDB and Spring Boot with PostgreSQL). It allows users to browse and search movies through a modular and scalable design.  
The **frontend** offers a clean UI with dropdown search (by title, genre, or actor), paginated poster grid, detailed movie pages and a lightweight real-time chat.  
The **MongoDB backend** manages dynamic data using Mongoose and async REST APIs, while the **Spring Boot backend** handles relational data with a layered JPA-based architecture.  
Data were cleaned and normalized using **Python (Pandas)** to ensure consistency across databases.  


Focus areas: **modularity**, **performance**, and **usability**.  

**Deliverables:**
- `queryexamples/` Some image exmaple of the web application ui
- `mainServer/` Complete Report of the project
- `solution/` Source Code
   - `mainServer/` Express.js + Hbs frontend 
   - `mongoServer/` MongoDB + Express.js backend  
   - `serverSpringboot/` SpringBoot + PostgreSQL backend
     
**Video Demo:** [Movies Library DEMO](https://youtu.be/URryv5dAq6s?si=dMAioZ_mOfz-wwFQ)




