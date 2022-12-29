package io.cherrytechnologies.pokemonwebclient.controller;

import io.cherrytechnologies.pokemonwebclient.io.entity.Pokemon;
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
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable int id) {
        return ResponseEntity.ok(service.getPokemonBy(id));
    }

    @GetMapping("/between")
    public ResponseEntity<List<Pokemon>> getPokemonBetweenId(@RequestParam int start, @RequestParam int end){
        return ResponseEntity.ok(service.getPokemonBetweenRange(start,end));
    }
}
