package pl.mg.blog.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutingConfiguration {

    private static final String API_VERSION_PREFIX = "/api/v1/";

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(p -> p
                        .path(API_VERSION_PREFIX + "get")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri("https://onet.pl"))
                .route(p -> p
                        .path(API_VERSION_PREFIX + "user-service/**")
                        .filters(f -> f.rewritePath(API_VERSION_PREFIX + "user-service/(?<segment>.*)", "/$\\{segment}"))
                        .uri("lb://user-service"))
                .route(p -> p
                        .path(API_VERSION_PREFIX + "simulation-service/**")
                        .filters(f -> f.rewritePath(API_VERSION_PREFIX + "simulation-service/(?<segment>.*)", "/$\\{segment}"))
                        .uri("lb://simulation-service"))
                .route(p -> p
                        .path(API_VERSION_PREFIX + "post-service/**")
                        .filters(f -> f.rewritePath(API_VERSION_PREFIX + "post-service/(?<segment>.*)", "/$\\{segment}"))
                        .uri("lb://post-service"))
                .route(p -> p
                        .path(API_VERSION_PREFIX + "comment-service/**")
                        .filters(f -> f.rewritePath(API_VERSION_PREFIX + "comment-service/(?<segment>.*)", "/$\\{segment}"))
                        .uri("lb://comment-service"))
                .build();
    }

}
