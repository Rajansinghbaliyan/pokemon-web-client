package io.cherrytechnologies.pokemonwebclient.web;

import io.cherrytechnologies.pokemonwebclient.exceptions.NotAbleToGet;
import io.cherrytechnologies.pokemonwebclient.io.entity.Pokemon;
import io.cherrytechnologies.pokemonwebclient.utils.CacheNameConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Operators;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Component
@Slf4j
public class PokemonWeb {

    @Autowired
    private WebClient client;

    @Autowired
    private CacheNameConstants constants;

    @Cacheable(cacheNames = "pokemon-form-web")
    public Optional<Pokemon> getPokemonById(int id) {
        return client
                .get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Pokemon.class)
                .onErrorResume(Mono::error)
                .retryWhen(Retry.backoff(2, Duration.ofSeconds(1))
                        .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) ->
                                new NotAbleToGet(retrySignal.failure())))
                .blockOptional();
    }

    @Cacheable(cacheNames = "pokemon-form-web-without-exception")
    public Optional<Pokemon> getPokemonByIdNoException(int id) {
        try {
            return client
                    .get()
                    .uri("/{id}", id)
                    .retrieve()
                    .bodyToMono(Pokemon.class)
                    .onErrorResume(Mono::error)
                    .retryWhen(
                            Retry.backoff(2, Duration.ofSeconds(1))
                                    .doAfterRetry(retrySignal -> log.debug(retrySignal.toString()))
                    )
                    .blockOptional();
        }
        catch (Exception e){
            log.error(e.getMessage());
            return Optional.empty();
        }
    }

}
