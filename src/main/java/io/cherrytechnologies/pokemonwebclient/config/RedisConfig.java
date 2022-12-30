package io.cherrytechnologies.pokemonwebclient.config;

import io.cherrytechnologies.pokemonwebclient.utils.CacheNameConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@Configuration
public class RedisConfig {

    @Autowired
    public CacheNameConstants constants;

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(){
        return RedisCacheConfiguration
                .defaultCacheConfig(Thread.currentThread().getContextClassLoader())
                .entryTtl(Duration.ofMinutes(60))
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer customizer(){
        return (builder) -> builder
                .withCacheConfiguration(constants.CACHE_NAME_POKEMON_WEB,
                        RedisCacheConfiguration.defaultCacheConfig(Thread.currentThread().getContextClassLoader())
                                .entryTtl(Duration.ofDays(10))
                        )
                .withCacheConfiguration(constants.CACHE_NAME_POKEMON_DB,
                        RedisCacheConfiguration.defaultCacheConfig(Thread.currentThread().getContextClassLoader())
                                .entryTtl(Duration.ofDays(1))
                )
                .withCacheConfiguration(constants.CACHE_NAME_POKEMON_LIST_DB,
                        RedisCacheConfiguration.defaultCacheConfig(Thread.currentThread().getContextClassLoader())
                                .entryTtl(Duration.ofDays(10))
                        )
                .withCacheConfiguration(constants.CACHE_NAME_POKEMON_WEB_WITHOUT_EXCEPTION,
                        RedisCacheConfiguration.defaultCacheConfig(Thread.currentThread().getContextClassLoader())
                                .entryTtl(Duration.ofDays(10))
                        )
                .withCacheConfiguration("pokemon-find-all-pokemon-view",
                        RedisCacheConfiguration.defaultCacheConfig(Thread.currentThread().getContextClassLoader())
                                .entryTtl(Duration.ofDays(10))
                        );

    }
}
