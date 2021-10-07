### Services

- configuration-service - Spring Cloud Config server
- discovery-service - Spring Eureka server
- gateway-service - Initially build with Spring Cloud Gateway (test Zuul Netflix as well)
- user-service - 
- post-service - 
- comment-service - 

### Spring Cloud components

- Eureka - service registration
- Spring Config
- Sleuth - tracing system
- Zipkin - to analyze traces

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
