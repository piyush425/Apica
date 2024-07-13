# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built jar file from the build context into the container
COPY build/libs/UserService-0.0.1-SNAPSHOT.jar /app/userservice.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/userservice.jar"]

