package io.cherrytechnologies.pokemonwebclient.service.stream_practise.reduce;

import io.cherrytechnologies.pokemonwebclient.enums.PokemonTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/type/all")
    public ResponseEntity<List<TypeAndCountDto>> getAllType(){
        return ResponseEntity
                .ok(service.typeAndCountAll());
    }

    @GetMapping("/type/with-pokemon")
    public ResponseEntity<Map<String, List<String>>> getPokemonAndType(){
        return ResponseEntity.ok(service.getPokemonByType());
    }

    @GetMapping("/type/with-pokemon-set")
    public ResponseEntity<Map<String,List<String>>> getPokemonTypeParticular(){
        return ResponseEntity.ok(service.getPokemonByParticularTypeSet());
    }

}