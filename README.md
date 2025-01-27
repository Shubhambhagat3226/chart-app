# WebSocket Chat Application

This is a simple real-time chat application built using Spring Boot and WebSockets. It allows multiple users to connect, send messages, and see who is currently online.

## Features

*   **Real-time Messaging:** Users can send and receive messages instantly.
*   **User Presence:**  See a list of currently connected users.
*   **Simple GUI Client:** A basic Swing-based client application for easy interaction.

## Technologies Used

*   **Spring Boot:**  For the backend application.
*   **Spring WebSockets:**  For handling WebSocket connections and messaging.
*   **STOMP:** For messaging protocol over WebSockets.
*   **Java Swing:** For the client-side graphical user interface.
*   **Maven:** For building and managing the project.

## Getting Started

### Prerequisites

*   Java Development Kit (JDK) 23 or higher
*   Maven

### Running the Application

1.  **Clone the repository:**

    ```bash
    git clone https://github.com/Shubhambhagat3226/chart-app.git
    cd your-repo
    ```

2.  **Build the project:**

    ```bash
    ./mvnw clean package
    ```

3.  **Run the server:**

    ```bash
    java -jar target/webSocket_demo-0.0.1-SNAPSHOT.jar
    ```

    The server will start on `http://localhost:8080`.

4.  **Run the client:**
    Navigate to `src/main/java/com/example/webSocket_demo/client` and run the `App.java` file. This can usually be done directly from your IDE.
    
5.  **Interact**
    A pop up will appear for username input. Enter username and start chatting!

### Docker

You can also build and run the application using Docker:

1.  **Build the Docker image:**

    ```bash
    docker build -t websocket-chat-app .
    ```

2.  **Run the Docker container:**

    ```bash
    docker run -p 8080:8080 websocket-chat-app
    ```

    The application will be accessible on `http://localhost:8080`
    
    To run the client application, it has to run outside the docker container, as it is a swing based client.
