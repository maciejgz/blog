FROM adoptopenjdk/openjdk15

ARG JAR_FILE=target/comment-service.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=mock","/app.jar"]