package io.cherrytechnologies.pokemonwebclient.controller;

import io.cherrytechnologies.pokemonwebclient.dto.PokemonDto;
import io.cherrytechnologies.pokemonwebclient.io.projections.PokemonView;
import io.cherrytechnologies.pokemonwebclient.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService service;

    @GetMapping("/{id}")
    public ResponseEntity<PokemonDto> getPokemonById(@PathVariable int id) {
        return ResponseEntity.ok(service.getPokemonBy(id));
    }

    @GetMapping("/between")
    public ResponseEntity<List<PokemonDto>> getPokemonBetweenId(@RequestParam int start, @RequestParam int end) {
        return ResponseEntity.ok(service.getPokemonBetweenRange(start, end));
    }

//    @GetMapping("/")
//    public ResponseEntity<List<PokemonDto>> getAllPokemonWithoutProperties(@RequestParam String propertiesToIgnore){
//        return ResponseEntity.ok(service.findAll(propertiesToIgnore));
//    }

    @GetMapping("/")
    public ResponseEntity<List<PokemonDto>> getAllPokemonBySort(
            @RequestParam String orderOne,
            @RequestParam String propertiesOne,
            @RequestParam String orderTwo,
            @RequestParam String propertiesTwo
    ) {
        return ResponseEntity.ok(service.findAllBySort(orderOne, propertiesOne, orderTwo, propertiesTwo));
    }

    @GetMapping("/pokemon-view")
    public ResponseEntity<List<PokemonView>> getAllByPokemonView() {
        return ResponseEntity.ok(service.findAllByPokemonView());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<PokemonDto>> getAllPokemonByPage(
            @RequestParam int pageSize,
            @RequestParam int offset,
            @RequestParam Optional<String> sortBy,
            @RequestParam Optional<String> order
    ) {
        return ResponseEntity.ok(service.findAll(pageSize, offset, sortBy, order));
    }
}
