package io.cherrytechnologies.pokemonwebclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PokemonWebClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(PokemonWebClientApplication.class, args);
    }

}
