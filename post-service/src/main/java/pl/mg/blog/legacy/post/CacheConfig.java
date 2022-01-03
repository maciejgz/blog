package pl.mg.blog.legacy.post;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    public static final String POST_CACHE = "posts";

}
