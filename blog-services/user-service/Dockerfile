FROM adoptopenjdk/openjdk16

ARG JAR_FILE=target/user-service.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=mock","-jar","/app.jar"]