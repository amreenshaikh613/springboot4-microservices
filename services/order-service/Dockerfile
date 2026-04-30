# ---------- BUILD STAGE (Keep as is) ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# ---------- RUNTIME STAGE (Updated) ----------
# CHANGE: Replace eclipse-temurin:17-jdk-alpine with a Debian-based version
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
