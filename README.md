# No craft server with keycloak authentication.

#### What it is?

Just a simple spring boot reactive service serving random json. Assume server started on 9080.\
Uses swagger here: http://localhost:9080/swagger-ui.html
Latest assembled image can be found on dockerhub: https://hub.docker.com/u/dzmitryitechart

#### Prerequisites

Install `java11(jdk)`, `maven`, `docker`, `docker-compose`


#### How to build and publish to docker?

Make sure you are have docker registry credentials, and run:
```bash
mvn package jib:dockerBuild
```

pay attention to pom.xml:
```xml
<image.path>dzmitryitechart/nocraft-server:0.3</image.path>
```
will be published with tag 0.3 in above command.


#### How to run from docker-compose?

Docker compose will build local image tagged as `dzmitryitechart/nocraft-server:local`\
and run it locally with java remote debug enabled.

first build it:
```bash
docker-compose build
```

and then run it:
```bash
docker-compose up -d
```




