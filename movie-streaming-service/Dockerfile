FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
MAINTAINER digiplay
COPY target/video-streaming-service-0.0.1-SNAPSHOT.jar movie-service.jar
ENTRYPOINT ["java","-jar","video-streaming-service-0.0.1-SNAPSHOT.jar"]
EXPOSE 8081