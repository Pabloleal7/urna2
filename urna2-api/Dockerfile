# Dockerfile
FROM maven:3.8.3-openjdk-11 as builder

WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN mvn clean package

FROM openjdk:11-jre-slim

COPY --from=builder /app/target/urna2-api-0.0.1-SNAPSHOT.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]

