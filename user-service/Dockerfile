FROM adoptopenjdk/openjdk11:latest
MAINTAINER iquinto.com
VOLUME /tmp
EXPOSE 8080
ADD build/libs/user-service-0.0.1-SNAPSHOT.jar userservice.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/userservice.jar"]