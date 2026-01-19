FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-21-jdk -y
COPY . .
RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:21
COPY --from=build /target/*.jar ./myreads.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "myreads.jar"]