# Use a base image with Java and Maven installed
FROM maven:3-eclipse-temurin-21 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the project files into the container
COPY . .

# Build the application using Maven
RUN mvn clean
RUN mvn package -DskipTests

# Use a smaller base image for the final image
FROM eclipse-temurin:21.0.1_12-jdk-ubi9-minimal

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage into the container
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar ./app.jar

# Expose the port the application runs on
EXPOSE 8080

# Specify the command to run on container start
CMD ["java", "-jar", "app.jar", "-Dspring.profiles.active=production"]