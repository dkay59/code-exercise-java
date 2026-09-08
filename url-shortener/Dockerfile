# ---- Build Stage ----
FROM gradle:jdk25-ubi10 AS build
WORKDIR /app

COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
#RUN ./gradlew --no-daemon build || true

COPY src ./src
RUN ./gradlew --no-daemon clean build -x test

# ---- Runtime Stage ----
FROM eclipse-temurin:25-jdk-ubi10-minimal
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

ENV JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"

EXPOSE 8080
EXPOSE 5005
ENTRYPOINT ["java", "-jar", "app.jar"]
