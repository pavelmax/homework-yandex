FROM java:8-jdk-alpine
COPY ./target/homework.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-Dserver.port=8080", "-jar", "homework.jar"]