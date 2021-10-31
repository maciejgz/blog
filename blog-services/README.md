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
- Zipkin - to analyze traces
- Feign - declarative HTTP client
- Resilience4j - centralized circuit breaker placed in the Gateway App. Currently, used on client side in feign client.

## Configuration
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

### Environments and profiles
- dev - localhost dev platform without Docker
- mock - local platform with Docker compose
- k8s - kubernetes environment

### Run sonar analysis
- start sonar container
- Run maven command:
```
mvn verify sonar:sonar -Dsonar.host.url=http://localhost:9000
```
`verify` step is needed to get sonar-project.properties file by maven plugin

### Zipkin
Zipkin gathers traces of communication between services (HTTP calls and events).<br />
http://localhost:9411

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

or use spring boot plugin (the latest image version is set by default): <br />
```
clean package spring-boot:build-image -DskipTests
```

### Docker compose
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
- Spring Cloud Contract
- Spring Cloud Consul - as an alternative to config and Eureka discovery
- Spring Cloud Commons
- Spring Cloud Vault
- Spring Cloud Stream
- Spring Cloud Schema Registry
