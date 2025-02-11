FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /orders-service
COPY . /orders-service/.

RUN mvn clean install


FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /orders-service/target/*.jar /orders-service/*.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/orders-service/*.jar"]

