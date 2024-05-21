FROM maven:3.8.6-openjdk-18 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:latest
COPY --from=build /target/Shopy-0.0.1-SNAPSHOT.jar Shopy.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Shopy.jar"]