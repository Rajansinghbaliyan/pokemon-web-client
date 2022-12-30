package io.cherrytechnologies.pokemonwebclient.service;

import io.cherrytechnologies.pokemonwebclient.dto.PokemonDto;
import io.cherrytechnologies.pokemonwebclient.exceptions.NotAbleToGet;
import io.cherrytechnologies.pokemonwebclient.io.entity.Pokemon;
import io.cherrytechnologies.pokemonwebclient.io.projections.PokemonView;
import io.cherrytechnologies.pokemonwebclient.io.repository.PokemonRepository;
import io.cherrytechnologies.pokemonwebclient.web.PokemonWeb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
    public PokemonDto getPokemonBy(int id) {
        log.debug("Trying to get Pokemon form db: " + id);
        Optional<Pokemon> pokemonById = repository.getPokemonById(id);

        if (pokemonById.isEmpty()) {
            pokemonById = web.getPokemonById(id);
            pokemonById.ifPresent(this::save);
        }

        return pokemonById
                .map(Pokemon::pokemonToDto)
                .orElseThrow(NotAbleToGet::new);
    }

    @Cacheable(cacheNames = "pokemon-list-from-db")
    public List<PokemonDto> getPokemonBetweenRange(int start, int end) {
        log.debug("Getting the list of pokemon");

        TreeSet<Pokemon> pokemonsByIdBetween = repository.getPokemonsByIdBetween(start, end);

        Map<Integer, Pokemon> collect = pokemonsByIdBetween
                .stream()
                .parallel()
                .collect(Collectors.toMap(
                        Pokemon::getId,
                        Function.identity(),
                        BinaryOperator.minBy(Comparator.comparingInt(Pokemon::getId)),
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
                .forEach(pokemon -> collect.put(pokemon.getId(), pokemon));

        return collect.values().stream().map(Pokemon::pokemonToDto).toList();
    }

    public Pokemon save(Pokemon pokemon) {
        log.debug("Saving pokemon to db: " + pokemon.getId());
        pokemon.uuid = null;
        return repository.save(pokemon);
    }

    public List<PokemonDto> findAll(String propertiesToIgnore) {
        Pokemon pokemon = new Pokemon();

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase(propertiesToIgnore);

        Example<Pokemon> example = Example.of(pokemon, matcher);
        return repository.findAll(example).stream().map(Pokemon::pokemonToDto).toList();
    }

    @Cacheable(cacheNames = "pokemon-find-all-pokemon-view")
    public List<PokemonView> findAllByPokemonView(){
        return repository
                .findAll()
                .stream()
                .parallel()
                .map(Pokemon::pokemonToPokemonView)
                .toList();
    }
}
