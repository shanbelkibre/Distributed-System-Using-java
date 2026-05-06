# Distributed System Data Chat App

This project is a simple Java socket-based client/server application that stores incoming messages in a MySQL database.

## Project Structure

- `src/App.java` - basic entry point used for quick testing.
- `src/Server.java` - starts the TCP server on port `1234` and accepts client connections.
- `src/ClientHandler.java` - handles one connected client, reads messages, and saves them to the database.
- `src/Client.java` - console client that sends user input to the server.
- `src/Client2.java` - second console client with the same behavior as `Client`.
- `src/DBConnection.java` - opens the JDBC connection to MySQL.
- `bin/` - compiled class files.
- `lib/` - external libraries and JDBC driver jars, if needed.

## How It Works

1. The server listens on port `1234`.
2. Each client connection is handled in a separate thread.
3. Every message received from a client is inserted into the `messages` table.
4. The server sends a confirmation response back to the client.

## Requirements

- Java Development Kit (JDK)
- MySQL server running locally
- A database named `chatdb`
- A table named `messages`

Example table schema:

```sql
CREATE DATABASE chatdb;

USE chatdb;

CREATE TABLE messages (
	id INT AUTO_INCREMENT PRIMARY KEY,
	message VARCHAR(255) NOT NULL
);
```

## Database Configuration

The JDBC settings are defined in `src/DBConnection.java`.

If your MySQL username, password, host, or database name is different, update that file before running the server.

## Build And Run

Compile the sources into `bin/`:

```bash
javac -d bin src/*.java
```

Run the server:

```bash
java -cp bin Server
```

Run one or more clients in separate terminals:

```bash
java -cp bin Client
```

```bash
java -cp bin Client2
```

Type messages in the client terminal to send them to the server. Type `exit` in `Client2` to close that client.

## Notes

- The current code expects MySQL to be available on `localhost:3306`.
- `DBConnection.java` currently contains hardcoded credentials, so move them to a safer configuration if you plan to deploy or share the project.
