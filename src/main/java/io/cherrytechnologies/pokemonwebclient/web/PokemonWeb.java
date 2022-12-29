package io.cherrytechnologies.pokemonwebclient.web;

import io.cherrytechnologies.pokemonwebclient.io.entity.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class PokemonWeb {

    @Autowired
    private WebClient client;

    public Mono<Pokemon> getPokemonById(int id){
        return client
                .get()
                .uri("/{id}",id)
                .retrieve()
                .bodyToMono(Pokemon.class);
    }
}
