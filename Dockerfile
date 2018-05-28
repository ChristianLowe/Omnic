FROM openjdk:8-jre-alpine
COPY target/Omnic-*.jar /app.jar
CMD ["/usr/bin/java", "-jar", "/app.jar"]


