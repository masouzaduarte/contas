FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY target/contas-api.jar contas-api.jar
ENTRYPOINT ["java","-jar","/contas-api.jar"]