FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
MAINTAINER digiplay
COPY target/auth-service.jar auth-service.jar
ENTRYPOINT ["java","-jar","auth-service.jar"]
EXPOSE 8081