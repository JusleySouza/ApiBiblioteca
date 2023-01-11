FROM openjdk:11-jdk-slim
WORKDIR /app
COPY target/library-api-1.0.0.jar /app/library_management.jar
ARG JAR_FILE=*jar
ENTRYPOINT ["java", "-jar", "library_management.jar"]