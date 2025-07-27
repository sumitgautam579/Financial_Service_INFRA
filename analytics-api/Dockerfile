# analytics-api/Dockerfile
FROM eclipse-temurin:11-jdk-alpine
WORKDIR /app

# Pin to the exact fat-jar you just built
ARG JAR_FILE=target/analytics-api-0.1.0.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080

# Use the Spring-Boot loader so Start-Class is honored
ENTRYPOINT ["java","-jar","/app/app.jar"]

