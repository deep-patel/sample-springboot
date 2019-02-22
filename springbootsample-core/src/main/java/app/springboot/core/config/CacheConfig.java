package app.springboot.core.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.stats.StatsCounter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by: deep.patel on 22/02/19
 */
@Slf4j
@ConfigurationProperties(prefix = "cache")
@Data
@Configuration
public class CacheConfig {
    private Map<String, Config> configs;

    @Bean
    public CacheManager cacheManager() {
        List<CaffeineCache> caffeineCaches = new ArrayList<>();
        for (Map.Entry<String, Config> entry : configs.entrySet()) {
            String cacheName = entry.getKey();
            Config config = entry.getValue();
            log.info("Initializing Cache {}, Config {}", cacheName, config);
            caffeineCaches.add(buildCache(cacheName, config.getTtl(), config.getTtlUnit(), config.getSize(),
                    null));
        }
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(caffeineCaches);
        return manager;
    }

    private CaffeineCache buildCache(String name, int minutesToExpire, TimeUnit ttlUnit, int size,
                                     StatsCounter statsCounter) {
        return new CaffeineCache(name, Caffeine.newBuilder().expireAfterWrite(minutesToExpire, ttlUnit)
                .maximumSize(size).build());
    }

    @Data
    public static class Config {
        private int      size;
        private int      ttl;
        private TimeUnit ttlUnit;
    }
}
