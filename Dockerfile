FROM openjdk:8

ARG DEBIAN_FRONTEND=noninteractive

RUN apt-get update && apt-get install -y bash

RUN addgroup spring && useradd -M spring -g spring

USER spring:spring

COPY target/app-exec.jar /opt/

ENTRYPOINT ["java","-jar","/opt/app-exec.jar"]