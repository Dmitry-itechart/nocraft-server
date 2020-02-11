# Nocraft + github client server

#### What it is?

Simple spring boot server capable to publish edits from froala UI editor to github.
Assume server started on 9080.
Latest assembled image can be found on dockerhub: https://hub.docker.com/u/dzmitryitechart

#### Prerequisites

Install `java11(jdk)`, `maven`, `docker`, `docker-compose`


#### How to build and publish to docker?

Make sure you are have docker registry credentials, and run:
```bash
mvn clean package
```

pay attention to pom.xml:
```xml
<image.path>dzmitryitechart/nocraft-server:0.X</image.path>
```
will be published with tag `0.X` in above command.

Images will be published here: https://hub.docker.com/u/dzmitryitechart

#### How to run from docker-compose?

Docker compose will build local image tagged as `dzmitryitechart/nocraft-server:<tag>`\
and run it locally with java remote debug enabled.

first, build it with maven:
```bash
mvn package
```
If you have IDE you can run it from IDE or run it as regular spring boot service. If you don't have IDE or not
familiar with spring boot, you may continue, and run it as locally published container via docker-compose:

...so build local docker container:
```bash
docker-compose build
```

...and then run it locally:
```bash
docker-compose up -d
```

Pay attention you have to have locally running keycloak instance to have ability to authenticate. You may consider
 [this](https://github.com/Dmitry-itechart/docker-compose) project to run all together.
 

 