FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY build/libs/configserver.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]