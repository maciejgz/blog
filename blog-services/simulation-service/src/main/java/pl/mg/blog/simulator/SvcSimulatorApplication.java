package pl.mg.blog.simulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SvcSimulatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SvcSimulatorApplication.class, args);
    }

}
