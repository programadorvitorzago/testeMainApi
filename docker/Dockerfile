# Alpine Linux with OpenJDK JRE
FROM openjdk:8-jre-alpine

# Copy war file
COPY mainAPI-0.0.1-SNAPSHOT.jar /mainAPI.jar

# run the app
CMD ["/usr/bin/java", "-jar", "/mainAPI.jar"]
