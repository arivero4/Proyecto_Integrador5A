FROM maven:3.8.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY java/predios-service/pom.xml ./java/predios-service/
COPY java/predios-service/src ./java/predios-service/src
RUN mvn clean package -DskipTests -f java/predios-service/pom.xml

FROM eclipse-temurin:17-jre-slim
WORKDIR /app
COPY --from=builder /app/java/predios-service/target/predios-service.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
