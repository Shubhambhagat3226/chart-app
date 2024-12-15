FROM maven:3.9.6-eclipse-temurin-22-jammy as build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:23-jdk
COPY --from=build /target/webSocket_demo-0.0.1-SNAPSHOT.jar webSocket_demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "webSocket_demo.jar"]