FROM adoptopenjdk/openjdk11:latest
MAINTAINER iquinto.com
VOLUME /tmp
ADD build/libs/eureka-server-0.0.1-SNAPSHOT.jar eurekaserver.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/eurekaserver.jar"]