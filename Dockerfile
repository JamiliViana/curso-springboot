FROM openjdk:8
MAINTAINER jamiliviana
WORKDIR /app

ARG JAR_FILE=target/tuor-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
