FROM maven:3.8.6-openjdk-18 AS build

# Set the working directory inside the Docker container
WORKDIR /app

# Copy the entire project directory into the working directory
COPY . /app

# Run the Maven build
RUN mvn clean package -DskipTests

FROM maven:3.8.6-openjdk-18

# Copy the jar file to the runtime image
COPY --from=build /app/target/Shopy-0.0.1-SNAPSHOT.jar Shopy.jar

# Expose the port
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "Shopy.jar"]