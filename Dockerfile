FROM openjdk:11

RUN addgroup spring && useradd -M spring -g spring

USER spring:spring

COPY target/app-exec.jar /opt/

ENTRYPOINT ["java","-jar","/opt/app-exec.jar"]
