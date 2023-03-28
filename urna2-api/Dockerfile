FROM maven:3.8.3-jdk-11 AS build
WORKDIR /urna2-api
COPY . /urna2-api
RUN mvn clean package -DskipTests

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /urna2-api/target/urna2-api-0.0.1-SNAPSHOT.jar /app/urna2-api-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "urna2-api-0.0.1-SNAPSHOT.jar"]
