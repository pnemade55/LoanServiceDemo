FROM openjdk:17-jdk-alpine

LABEL maintainer=pnemade55@gmail.com

RUN addgroup -S demo && adduser -S demo -G demo
USER demo:demo

COPY build/libs/loan-service-demo-0.0.1-SNAPSHOT.jar loan-service-demo.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","/loan-service-demo.jar"]