FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY build/libs/apigateway.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]