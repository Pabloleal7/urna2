# Dockerfile
FROM openjdk:11

COPY target/urna2-api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
