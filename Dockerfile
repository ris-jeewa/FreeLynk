# Use an official JDK runtime as base
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file (replace with your jar name or use wildcard)
COPY target/*.jar FreeLynk-0.0.1-SNAPSHOT.jar

# Expose port (same as in application.properties, usually 8080)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "FreeLynk-0.0.1-SNAPSHOT.jar"]
