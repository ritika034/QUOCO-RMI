FROM openjdk:8-jre-alpine
ENV SERVER_HOST=localhost
COPY target/auldfellas-1.0-SNAPSHOT.jar /auldfellas.jar
CMD ["/usr/bin/java", "-cp", "/auldfellas-1.0-SNAPSHOT.jar", "EchoClient", "server"]
