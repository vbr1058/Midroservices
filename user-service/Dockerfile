FROM maven:3.9.4-eclipse-temurin-21-alpine

WORKDIR /app

# Copy Maven wrapper and POM files
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Ensure the Maven wrapper script is executable
RUN chmod +x mvnw

# Copy the source code
COPY src ./src

# Default command to run the application
CMD ["/app/mvnw", "spring-boot:run"]
