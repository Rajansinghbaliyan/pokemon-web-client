package io.cherrytechnologies.pokemonwebclient.service.stream_practise.flatmap;

import io.cherrytechnologies.pokemonwebclient.dto.TypeDto;
import io.cherrytechnologies.pokemonwebclient.service.PokemonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FlatmapService {

    @Autowired
    private PokemonService service;

    /*
    * 2. Given two lists of numbers, how would you return all pairs of numbers? For example,
    *   given a list [1, 2, 3] and a list [3, 4] you should return [(1, 3), (1, 4), (2, 3), (2, 4),
    *   (3, 3), (3, 4)]. For simplicity, you can represent a pair as an array with two elements.
    *
    * */

    public List<List<Integer>> questionOne(){
        List<Integer> first = List.of(1, 2, 3);
        List<Integer> second = List.of(3, 4);

        return first
                .stream()
                .flatMap(num -> second
                        .stream()
                        .map(secondNum -> List.of(num,secondNum))
                )
                .toList();
    }


    public List<String> differentTypesOfPokemon(){
        log.debug(String.format("Class name: %s, get different types", getClass().getName()));
        return service.findAll("id","asc")
                .stream()
                .flatMap(pokemonDto -> pokemonDto.getTypes().stream())
                .map(TypeDto::getName)
                .distinct()
                .toList();
    }
}
