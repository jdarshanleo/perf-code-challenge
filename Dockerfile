FROM openjdk:11.0.7-jre-slim

WORKDIR /build
COPY build/libs/user-srvc.jar /build/user-srvc.jar

USER root

ENTRYPOINT ["java", "-jar", "/build/user-srvc.jar"]
