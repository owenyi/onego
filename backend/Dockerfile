FROM maven:3.8-jdk-11-slim AS builder
WORKDIR /app
COPY ./ ./
RUN mvn package

FROM openjdk:11-jre-slim
WORKDIR /app
ARG --from=builder JAR_FILE=/app/target/*.jar
COPY --from=builder ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]