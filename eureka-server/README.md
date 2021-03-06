# EUREKA (<i>Backend</i>)


## Requirementsr
* [JDK 11](https://www.oracle.com/es/java/technologies/javase/jdk11-archive-downloads.html)
* [Gradle version >= 5.0](https://docs.gradle.org/current/userguide/compatibility.html)
* [Intellj IDEA](https://www.jetbrains.com/idea/) or similar
* [Docker/Docker Desktop](https://www.docker.com/)


## Docker
### Docker build 
```
docker build -f Dockerfile -t eureka .
```

### Docker push to  hub 
```
docker tag eureka iquinto/hp-eureka
```

```
docker push iquinto/hp-eureka
```

### Docker run (for testing)
```
docker run -it -p 8761:8761 --rm --name eureka-server eureka-server
```


### Docker stop
```
docker stop $(docker ps -a -q)
docker rm -vf $(docker ps -a -q)
docker rmi -f $(docker images -a -q) 

```

