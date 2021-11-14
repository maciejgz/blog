## Description
### Services
- configuration-service - Spring Cloud Config server
- discovery-service - Spring Eureka server
- gateway-service - Initially build with Spring Cloud Gateway (test Zuul Netflix as well)
- user-service - User management service
- post-service - Post management service
- comment-service - Comments of posts with likes

### Spring Cloud components
- Eureka - service registration
- Spring Config
- Sleuth - tracing system
- Zipkin - to analyze traces (possibility to user Kafka as a source of traces)
- Kafka with Zookeeper - message broker
- Feign - declarative HTTP client
- Resilience4j - centralized circuit breaker placed in the Gateway App. Currently, used on client side in feign client.

## Configuration
### Requirements
- App should be built and run in Java 11
- Docker and Kubernetes
### Custom modules port mapping
|Module|Ports|
|------|-----|
|Spring Cloud Config server|8888|
|Eureka server|8761|
|Gateway service|8080|
|Simulation service|8099|
|User services|8100 - 8199|
|Post services|8200 - 8299|
|Comment services|8300 - 8399|

### External modules (docker)
|Module|Ports|
|------|-----|
|Sonar|9100|
|Zipkin server|9411|
|Kafka|29092|

### Profiles
- dev - localhost dev platform without Docker
- mock - local platform with Docker compose
- k8s - kubernetes environment

### Run sonar analysis
- start sonar container. Default user/pass: admin/admin
- Run maven command:
```
mvn verify sonar:sonar -Dsonar.host.url=http://localhost:9000
```
`verify` step is needed to get sonar-project.properties file by maven plugin

### Zipkin
Zipkin gathers traces of communication between services. <br />
For K8S environment Zipkin uses HTTP communication: http://localhost:9411. <br />
For dev and mock profiles, apps and Zipkin uses Kafka topic `zipkin` to gather traces. Kafka has to be started in docker compose.

### Kafka
Offset Explorer can be connected to the Kafka cluster run in docker compose with the following way: </br>
![offset_explorer_config_1.png](docs/img/offset_explorer_config_1.png) <br />
![offset_explorer_config_2.png](docs/img/offset_explorer_config_2.png)

## Build and run
### Build mvn project
```
mvn clean package
```

### build docker images
Run script <br />
```
.\build_images.sh
```

or use spring boot plugin for maven (the latest image version is set by default): <br />
```
clean package spring-boot:build-image -DskipTests
```
<b> WARNING: images built with spring boot have problems in kubernetes - memory issue</b>

### Docker compose environment
```
\docker\blog.yml
```

### Kubernetes environment
Go to `/k8s/blog/` where build scripts are defined and run commands: <br />
```
kubectl apply -f zipkin-service.yaml
kubectl apply -f user-service.yaml
kubectl apply -f post-service.yaml
kubectl apply -f gateway-service.yaml
kubectl apply -f comment-service.yaml
kubectl apply -f simulation-service.yaml
```

## TODO
- Spring Cloud Schema Registry
- Spring Cloud Commons
- Spring Cloud Vault
- Spring Cloud Stream
