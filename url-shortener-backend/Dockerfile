#
# Build stage
#
FROM maven:3.8.5-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:17
COPY --from=build /home/app/target/*.jar /usr/local/lib/runner.jar
EXPOSE 8080
LABEL authors="Eduardo"

ENTRYPOINT ["java", "-jar", "/usr/local/lib/runner.jar"]