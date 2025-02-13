FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /orders-service
COPY pom.xml .
RUN mvn clean install -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /orders-service
COPY --from=builder /orders-service/target/orders-service.jar /orders-service/orders-service.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/orders-service/*.jar"]

