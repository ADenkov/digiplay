FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
MAINTAINER digiplay
COPY target/subscription-service-1.0.0.jar subscription-service.jar
ENTRYPOINT ["java","-jar","subscription-service.jar"]
EXPOSE 8083