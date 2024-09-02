FROM node:18-alpine AS frontend-builder
WORKDIR /frontend
COPY ./frontend/package.json ./frontend/package-lock.json ./
RUN npm install
COPY ./frontend .
RUN npm run build
FROM openjdk:17-slim AS backend-builder
WORKDIR /
COPY ./pom.xml ./
COPY ./src ./src
RUN apt-get update && apt-get install -y maven && mvn dependency:go-offline -B
COPY --from=frontend-builder /frontend/public/dist ./src/main/resources/static/
RUN mvn clean package -DskipTests
FROM openjdk:17-slim
WORKDIR /
COPY --from=backend-builder target/example-extension-java-vue-0.0.1-SNAPSHOT.jar ./example-extension-java-vue-0.0.1-SNAPSHOT.jar
COPY src/main/resources/application-prod.yml ./application-prod.yml
ENV SPRING_PROFILES_ACTIVE=prod
EXPOSE 8080
CMD ["java", "-jar", "example-extension-java-vue-0.0.1-SNAPSHOT.jar"]
