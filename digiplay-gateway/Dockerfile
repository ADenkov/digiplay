FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
MAINTAINER digiplay
COPY build/libs/digiplaygateway-0.0.1-SNAPSHOT.jar digiplaygateway-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","digiplaygateway-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080