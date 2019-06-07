FROM openjdk:8-alpine
VOLUME /tmp
VOLUME /data

ADD app.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]