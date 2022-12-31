package io.cherrytechnologies.pokemonwebclient.service.stream_practise.slice;

import io.cherrytechnologies.pokemonwebclient.dto.PokemonDto;
import io.cherrytechnologies.pokemonwebclient.io.projections.PokemonView;
import io.cherrytechnologies.pokemonwebclient.io.repository.PokemonRepository;
import io.cherrytechnologies.pokemonwebclient.service.PokemonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SliceService {
    @Autowired
    private PokemonRepository repository;
    @Autowired
    private PokemonService service;
    /*
     * Java 9 added two new methods that are useful for efficiently selecting elements in a stream: takeWhile and dropWhile.
     *
     * It lets you slice any stream (even an infinite stream as you will
     * learn later) using a predicate. But thankfully, it stops once it has found an element
     * that fails to match. Here’s how you can use it:
     * List<Dish> slicedMenu1
     *   = specialMenu.stream()
     *       .takeWhile(dish -> dish.getCalories() < 320)
     *       .collect(toList());
     *
     * */

    @Cacheable(cacheNames = "find-all-default")
    public List<PokemonDto> getPokemonWhileId(int takeWhileId) {
        log.debug("Slice Service: get pokemon take while");
        return service
                .findAll("id", "asc")
                .stream()
                .takeWhile(pokemonDto -> pokemonDto.id < takeWhileId)
                .toList();
    }

    @Cacheable(cacheNames = "find-all-view-default")
    public List<PokemonView> getPokemonViewWhileId(int takeWhileId) {
        log.debug("Slice Service: get pokemon view take while");
        return service
                .findAllPokemonView("id", "asc")
                .stream()
                .takeWhile(pokemonView -> pokemonView.getId() < takeWhileId)
                .toList();
    }

    /*
    The dropWhile operation is the complement of takeWhile.
    It throws away the elements at the start where the predicate is false. Once the predicate evaluates to true it
    stops and returns all the remaining elements, and it even works if there are an infinite
    number of remaining elements!
    */


}
