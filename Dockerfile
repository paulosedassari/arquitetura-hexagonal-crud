    FROM maven:3.9.8-amazoncorretto-17 AS build

    COPY src /app/src
    COPY pom.xml /app

    WORKDIR /app
    RUN mvn clean install

    FROM openjdk:17-jdk-alpine

    COPY --from=build /app/target/cashme-service-0.0.1-SNAPSHOT.jar /app/app.jar

    WORKDIR /app

    EXPOSE 8080

    CMD ["java", "-jar", "app.jar"]