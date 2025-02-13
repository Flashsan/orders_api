FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /number-generate-service
COPY pom.xml .
RUN mvn clean install -DskipTests


FROM eclipse-temurin:17-jre-alpine
WORKDIR /number-generate-service
COPY --from=builder /number-generate-service/target/number-generate-service.jar /number-generate-service/number-generate-service.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/number-generate-service/*.jar"]