package pl.mg.blog.post;

import brave.sampler.Sampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracingConfig {

    /**
     * Required to tell sleuth that each request shall be sent to Zipkin.
     *
     * @return sampler object
     */
    @Bean
    public Sampler alwaysSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}
