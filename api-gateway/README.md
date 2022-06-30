# GATEWAY (<i>Backend</i>)


## Requirements
* [JDK 11](https://www.oracle.com/es/java/technologies/javase/jdk11-archive-downloads.html)
* [Gradle version >= 5.0](https://docs.gradle.org/current/userguide/compatibility.html)
* [Intellj IDEA](https://www.jetbrains.com/idea/) or similar
* [Docker/Docker Desktop](https://www.docker.com/)


## Docker
### Docker build 
```
docker build -t api-gateway .
```

### Docker push to  hub 
```
docker tag api-gateway iquinto/api-gateway
```

```
docker push iquinto/api-gateway
```

### Docker run (for testing)
```
docker run -it -p 8081:81 --rm --name api-gateway api-gateway
```


### Docker stop
```
docker stop $(docker ps -a -q)
docker rm -vf $(docker ps -a -q)
docker rmi -f $(docker images -a -q) 

```

