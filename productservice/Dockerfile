FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY build/libs/productservice.jar app.jar

ENTRYPOINT ["java", "-jar", "--add-opens=java.base/java.time=ALL-UNNAMED", "app.jar"]