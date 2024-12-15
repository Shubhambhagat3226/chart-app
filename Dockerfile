# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-22-jammy AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
ENV PATH="/app:${PATH}"
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:23-jdk
COPY --from=build /app/target/webSocket_demo-0.0.1-SNAPSHOT.jar webSocket_demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "webSocket_demo.jar"]

