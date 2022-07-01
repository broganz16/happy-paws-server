FROM adoptopenjdk/openjdk11:latest
MAINTAINER iquinto.com
VOLUME /tmp
ADD build/libs/api-gateway-0.0.1-SNAPSHOT.jar apigateway.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/apigateway.jar"]