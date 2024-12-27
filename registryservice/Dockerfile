FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY build/libs/registryservice.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]