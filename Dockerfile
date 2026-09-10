# # 1. Build React
# FROM node:24 AS react-build
# WORKDIR /app/frontend
# COPY frontend/package*.json ./
# RUN npm install
# COPY frontend/ .
# RUN npm run build

# # 2. Build Spring Boot
# FROM gradle:jdk25-ubi10 AS spring-build
# WORKDIR /app/backend
# COPY backend/ .
# RUN ./gradlew build -x test

# # 3. Final image
# FROM eclipse-temurin:25-jdk-ubi10-minimal
# WORKDIR /app

# # Copy Spring Boot JAR
# COPY --from=spring-build /app/backend/build/libs/*.jar app.jar

# # Copy React build into Spring Boot static folder
# COPY --from=react-build /app/frontend/build /app/static

# EXPOSE 8080
# ENTRYPOINT ["java", "-jar", "app.jar"]