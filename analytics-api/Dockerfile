FROM eclipse-temurin:11-jdk-alpine
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["sh","-c", "java -cp apphealth.jar com.financial.service.App"]
