FROM maven:3.9.12-amazoncorretto-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -DskipTests package


FROM eclipse-temurin:21-jre-alpine-3.23
WORKDIR /app
COPY --from=build /app/target/agendamento-0.0.1.jar ./agendamento.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "agendamento.jar"]