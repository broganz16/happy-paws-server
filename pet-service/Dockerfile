FROM adoptopenjdk/openjdk11:latest
MAINTAINER iquinto.com
VOLUME /tmp
ADD build/libs/pet-service-0.0.1-SNAPSHOT.jar userservice.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/userservice.jar"]