# Use the official OpenJDK image as the base image
FROM openjdk:11-jdk-slim

# Set the working directory inside the container
WORKDIR /elysium-os

# Copy the JAR file into the container
COPY ./build/ElysiumOS.jar /elysium-os/ElysiumOS.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "ElysiumOS.jar"]