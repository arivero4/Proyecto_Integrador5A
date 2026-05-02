FROM maven:3.8.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY java/inspecciones-service/pom.xml ./java/inspecciones-service/
COPY java/inspecciones-service/src ./java/inspecciones-service/src
RUN mvn clean package -DskipTests -f java/inspecciones-service/pom.xml

FROM eclipse-temurin:17-jre-slim
WORKDIR /app
COPY --from=builder /app/java/inspecciones-service/target/inspecciones-service.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]
