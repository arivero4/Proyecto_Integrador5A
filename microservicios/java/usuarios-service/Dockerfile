FROM maven:3.8.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY java/usuarios-service/pom.xml ./java/usuarios-service/
COPY java/usuarios-service/src ./java/usuarios-service/src
RUN mvn clean package -DskipTests -f java/usuarios-service/pom.xml

FROM eclipse-temurin:17-jre-slim
WORKDIR /app
COPY --from=builder /app/java/usuarios-service/target/usuarios-service.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
