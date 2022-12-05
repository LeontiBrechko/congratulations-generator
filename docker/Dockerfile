FROM openjdk:17

COPY ../build/libs/congratulations-generator-0.0.1-SNAPSHOT.jar /app.jar
COPY ../src/main/resources/keystore.jks /src/main/resources/keystore.jks

ENTRYPOINT ["java","-jar","/app.jar"]