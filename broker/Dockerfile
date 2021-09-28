FROM openjdk:8-jre-alpine
COPY target/broker-1.0-SNAPSHOT-shaded.jar /broker.jar
CMD ["/usr/bin/java", "-cp", "/broker.jar", "Server"]