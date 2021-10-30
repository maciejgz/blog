FROM adoptopenjdk/openjdk15

ARG JAR_FILE=target/simulation-service.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]