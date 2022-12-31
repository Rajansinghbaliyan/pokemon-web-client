package io.cherrytechnologies.pokemonwebclient.service;

import io.cherrytechnologies.pokemonwebclient.dto.PokemonDto;
import io.cherrytechnologies.pokemonwebclient.exceptions.NotAbleToGet;
import io.cherrytechnologies.pokemonwebclient.io.entity.Pokemon;
import io.cherrytechnologies.pokemonwebclient.io.projections.PokemonView;
import io.cherrytechnologies.pokemonwebclient.io.repository.PokemonRepository;
import io.cherrytechnologies.pokemonwebclient.utils.BuildPage;
import io.cherrytechnologies.pokemonwebclient.utils.BuildSort;
import io.cherrytechnologies.pokemonwebclient.utils.SortOrder;
import io.cherrytechnologies.pokemonwebclient.web.PokemonWeb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
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

    @Cacheable(cacheNames = "pokemon-find-all-sort-two")
    public List<PokemonDto> findAllBySort(String orderOne, String propertiesOne, String orderTwo, String propertiesTwo) {
        log.debug("Getting all pokemon by sort");
        return repository
                .findAll(
                        Sort.by(BuildSort.buildSort(SortOrder.valueOf(orderOne)), propertiesOne)
                                .and(Sort.by(BuildSort.buildSort(SortOrder.valueOf(orderTwo)), propertiesTwo))
                )
                .stream()
                .map(Pokemon::pokemonToDto)
                .toList();
    }

    @Cacheable(cacheNames = "pokemon-find-all-pokemon-view")
    public List<PokemonView> findAllByPokemonView() {
        log.debug("Getting all pokemon view");
        return repository
                .findAll()
                .stream()
                .parallel()
                .map(Pokemon::pokemonToPokemonView)
                .toList();
    }

    @Cacheable(cacheNames = "pokemon-find-all-page")
    public Page<PokemonDto> findAll(int pageSize, int offset, Optional<String> properties, Optional<String> order) {
        log.debug("Get all pokemon by page");
        Pageable pageable = PageRequest.of(
                BuildPage.pageNumber(pageSize, offset),
                pageSize,
                Sort.by(
                        order.map(o -> BuildSort.buildSort(SortOrder.valueOf(o)))
                                .orElse(Sort.Direction.ASC),
                        properties
                                .orElseGet(() -> "id")
                )
        );
        return repository
                .findAll(pageable)
                .map(Pokemon::pokemonToDto);
    }

    @Cacheable(cacheNames = "find-all-default")
    public List<PokemonDto> findAll(String properties, String order) {
        log.debug("Get all pokemon with only sort");
        return repository
                .findAll(Sort.by(BuildSort.buildSort(SortOrder.valueOf(order)), properties))
                .stream()
                .map(Pokemon::pokemonToDto)
                .toList();
    }

    @Cacheable(cacheNames = "find-all-default")
    public List<PokemonView> findAllPokemonView(String properties, String order) {
        log.debug("Get all pokemon with only sort");
        return repository
                .findAll(Sort.by(BuildSort.buildSort(SortOrder.valueOf(order)), properties))
                .stream()
                .map(Pokemon::pokemonToPokemonView)
                .toList();
    }
}
