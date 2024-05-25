# FROM maven:3.8.6-openjdk-18 AS build
#
# # Set the working directory inside the Docker container
# WORKDIR /app
#
# # Copy the entire project directory into the working directory
# COPY . /app
#
# # Run the Maven build
# RUN mvn clean package -DskipTests
#
# FROM eclipse-temurin:latest
#
# # Copy the jar file to the runtime image
# COPY --from=build /app/target/Shopy-0.0.1-SNAPSHOT.jar app.jar
#
# # Expose the port
# EXPOSE 8080
#
# # Run the jar file
# # ENTRYPOINT ["java", "-Dserver.port=$PORT", "-jar", "/app.jar"]
# ENTRYPOINT ["java", "-Xmx512m", "-jar", "/app.jar"]

# Use an Azul Zulu OpenJDK 22 image for the build stage
FROM azul/zulu-openjdk-debian:22.0.0 AS build

# Set the working directory inside the container
WORKDIR /app

# Install Maven
RUN apt-get update && apt-get install -y maven

# Copy the Maven project file
COPY Shopy/pom.xml .

# Download and cache the dependencies
RUN mvn dependency:go-offline -B

# Copy the entire project
COPY Shopy .

# Build the application
RUN mvn package -DskipTests

# Use the Azul Zulu OpenJDK 22 image for the runtime stage
FROM azul/zulu-openjdk-debian:22.0.0

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/Shopy-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application runs on (replace 8080 if your app runs on a different port)
EXPOSE 8080

# Set the entry point to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]

