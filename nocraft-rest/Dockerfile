FROM openjdk:11

RUN apt-get update && apt-get install -y expect

# or you can mount expected known_hosts if necessary
RUN mkdir ~/.ssh
RUN ssh-keyscan -H bitbucket.org >> ~/.ssh/known_hosts

COPY /nocraft-rest/docker/push.sh /opt/push.sh
COPY /nocraft-rest/docker/start.sh /opt/start.sh
RUN chmod +x /opt/start.sh
RUN chmod +x /opt/push.sh

COPY /nocraft-rest/target/app-exec.jar /opt/

ENTRYPOINT ["/opt/start.sh"]
