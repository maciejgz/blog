FROM adoptopenjdk/openjdk16

ARG JAR_FILE=target/post-service.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=mock","/app.jar"]