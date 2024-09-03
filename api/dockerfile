FROM amazoncorretto:21-alpine3.20

COPY target/telemedicina-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java","-jar","/app.jar" ]

EXPOSE 8090