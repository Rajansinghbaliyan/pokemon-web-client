package io.cherrytechnologies.pokemonwebclient.service.stream_practise.reduce;

import io.cherrytechnologies.pokemonwebclient.enums.PokemonTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pokemon/reduce")
public class ReduceController {

    @Autowired
    ReduceService service;

    @GetMapping("/type")
    public ResponseEntity<TypeAndCountDto> getTypeAndNoOfPokemon(@RequestParam String types){
        return ResponseEntity.ok(service.typeAndCount(PokemonTypes.valueOf(types)));
    }

    @GetMapping("/type/simple")
    public ResponseEntity<TypeAndCountDto> getTypeAndNoOfPokemonSimple(@RequestParam String types){
        return ResponseEntity.ok(service.typeAndCountSimple(PokemonTypes.valueOf(types)));
    }

}
