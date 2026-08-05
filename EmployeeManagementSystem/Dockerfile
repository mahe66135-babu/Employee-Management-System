# ---------- Build Stage ----------
FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn
COPY src src

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# ---------- Runtime Stage ----------
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]