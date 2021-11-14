FROM adoptopenjdk/openjdk16

ARG JAR_FILE=target/configuration-service.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]