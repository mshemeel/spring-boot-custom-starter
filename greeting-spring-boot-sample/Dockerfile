FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy the pre-built JAR file from the target directory
COPY target/greeting-spring-boot-sample-0.0.1-SNAPSHOT.jar app.jar

# Set environment variables (optional)
ENV SERVER_PORT=8080

EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"] 