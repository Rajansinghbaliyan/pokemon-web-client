package io.cherrytechnologies.pokemonwebclient.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheNameConstants {
    @Value("${cache.pokemon.web}")
    public String CACHE_NAME_POKEMON_WEB;
    @Value("${cache.pokemon.db}")
    public String CACHE_NAME_POKEMON_DB;
    @Value("${cache.pokemon.list.db}")
    public String CACHE_NAME_POKEMON_LIST_DB;
    @Value("${cache.pokemon.web.without_exception}")
    public String CACHE_NAME_POKEMON_WEB_WITHOUT_EXCEPTION;

}
