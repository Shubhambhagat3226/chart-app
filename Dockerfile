# Stage 1: Build the application
FROM openjdk:23-jdk AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:23-jdk
COPY --from=build /target/webSocket_demo-0.0.1-SNAPSHOT.jar webSocket_demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "webSocket_demo.jar"]


