
FROM openjdk:8-windowsservercore

LABEL maintainer="marcelbrittorj@gmail.com"

COPY target/BrasilprevRestApi-1.0.0.jar /BrasilprevRestApi.jar
EXPOSE 8080

CMD ["java", "-jar", "BrasilprevRestApi.jar"]