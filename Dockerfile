
#FROM eclipse-temurin:17-jdk-jammy
#WORKDIR /app
#COPY build/libs/*.jar springrestapp.jar
#ENTRYPOINT ["java","-jar","/springrestapp.jar"]
FROM eclipse-temurin:17
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
#RUN gradle build --no-daemon

#FROM openjdk:8-jre-slim

#EXPOSE 8080

COPY build/libs/*.jar mymoneyapp.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar","mymoneyapp.jar"]

