FROM maven:3.9.9-eclipse-temurin-21 AS build

COPY src /app/src
COPY pom.xml /app
COPY .env /app/.env

WORKDIR /app
RUN mvn clean install -DskipTests

FROM openjdk:21-jdk-slim

COPY --from=build /app/target/Lost-Pets-0.0.1-SNAPSHOT.jar /app/app.jar
COPY .env /app/.env

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]