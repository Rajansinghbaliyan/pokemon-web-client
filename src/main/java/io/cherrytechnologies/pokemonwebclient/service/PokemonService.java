package io.cherrytechnologies.pokemonwebclient.service;

import io.cherrytechnologies.pokemonwebclient.io.entity.Pokemon;
import io.cherrytechnologies.pokemonwebclient.io.repository.PokemonRepository;
import io.cherrytechnologies.pokemonwebclient.web.PokemonWeb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
public class PokemonService {
    @Autowired
    private PokemonWeb web;
    @Autowired
    private PokemonRepository repository;

    @Cacheable(cacheNames = "pokemon-from-db")
    public Pokemon getPokemonBy(int id) {
        Optional<Pokemon> pokemonById = repository.getPokemonById(id);

        if (pokemonById.isEmpty()) {
            log.debug("Getting Pokemon form web: " + id);
            pokemonById = web.getPokemonById(id);
            pokemonById.ifPresent(this::save);
        }

        return pokemonById.orElseThrow(RuntimeException::new);
    }

    @Cacheable(cacheNames = "pokemon-list-from-db")
    public List<Pokemon> getPokemonBetweenRange(int start, int end) {
        TreeSet<Pokemon> pokemonsByIdBetween = repository.getPokemonsByIdBetween(start, end);

        Map<Integer, Pokemon> collect = pokemonsByIdBetween
                .stream()
                .parallel()
                .collect(Collectors.toMap(
                        Pokemon::getId,
                        Function.identity(),
                        BinaryOperator.minBy(Comparator.comparingInt(p -> p.id)),
                        TreeMap::new
                ));

        IntStream.rangeClosed(start, end)
                .parallel()
                .filter(id -> !collect.containsKey(id))
                .mapToObj(id -> web
                        .getPokemonByIdNoException(id)
                        .map(this::save)
                )
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(pokemon -> collect.put(pokemon.id, pokemon));

        return collect.values().stream().toList();
    }

    public Pokemon save(Pokemon pokemon) {
        log.debug("Saving pokemon to db: " + pokemon.id);
        pokemon.uuid = null;
        return repository.save(pokemon);
    }
}
