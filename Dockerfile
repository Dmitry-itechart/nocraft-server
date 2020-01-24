FROM openjdk:8

RUN apt-get update && apt-get install -y bash mc

RUN useradd -M spring && usermod -a -G spring spring

USER spring:spring

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]