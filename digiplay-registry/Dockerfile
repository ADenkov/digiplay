FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
MAINTAINER digiplay
COPY build/libs/digiplay-registry-0.0.1-SNAPSHOT.jar digiplay-registry-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","digiplay-registry-0.0.1-SNAPSHOT.jar"]
EXPOSE 8761