package org.example.service.impl;

import io.swagger.v3.oas.annotations.servers.Server;
import org.example.service.GetValueService;
import org.springframework.cache.annotation.Cacheable;

@Server
public class GetValueServiceImpl implements GetValueService {

    // 这里会使用CacheConfig中的缓存
    @Cacheable(cacheManager = "testCacheManager", cacheNames = "mavenProjectTestCache")
    public String getValue(String key) {
        // 这里的return的值会被缓存，因为加了@Cacheable注解
        // 这里会缓存到redis，key为函数的参数， value为返回值
        return "value";
    }
}
