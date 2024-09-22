package org.example.config;

import java.time.Duration;
import java.util.Set;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
public class CacheConfig {

    // 这里设置了CacheManager之后，使用的地方可以使用@Cacheable来使用，相当于做了redis的缓存
    @Bean
    public CacheManager testCacheManager(RedisConnectionFactory connectionFactory) {
        final RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(600)) // 缓存10分钟
                .prefixCacheNameWith("test-key1:");
        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config)
                .transactionAware()
                .initialCacheNames(Set.of("mavenProjectTestCache"))
                .build();
    }
}
