swagger: http://localhost:8080/swagger-ui.html
dockerhub: https://hub.docker.com/u/dzmitryitechart

#### Prerequisites

Install `java8(jdk)`, `maven`, `docker`, `docker-compose`


#### How to build for local docker?

```bash
mvn package jib:dockerBuild
```
pay attention to:

```text
[INFO] Built image to Docker daemon as dzmitryitechart/nocraft-server
```
where `dzmitryitechart/nocraft-server` is container name published for local registry.


#### How to run from docker-compose?

```bash
docker-compose up -d
```




