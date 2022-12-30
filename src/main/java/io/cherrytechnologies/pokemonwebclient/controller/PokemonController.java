package io.cherrytechnologies.pokemonwebclient.controller;

import io.cherrytechnologies.pokemonwebclient.dto.PokemonDto;
import io.cherrytechnologies.pokemonwebclient.io.projections.PokemonView;
import io.cherrytechnologies.pokemonwebclient.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<PokemonDto>> getPokemonBetweenId(@RequestParam int start, @RequestParam int end){
        return ResponseEntity.ok(service.getPokemonBetweenRange(start,end));
    }

    @GetMapping("/")
    public ResponseEntity<List<PokemonDto>> getAllPokemonWithoutProperties(@RequestParam String propertiesToIgnore){
        return ResponseEntity.ok(service.findAll(propertiesToIgnore));
    }

    @GetMapping("/pokemon-view")
    public ResponseEntity<List<PokemonView>> getAllByPokemonView(){
        return ResponseEntity.ok(service.findAllByPokemonView());
    }
}
