FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY build/libs/inventoryservice.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]