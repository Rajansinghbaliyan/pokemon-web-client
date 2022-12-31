package io.cherrytechnologies.pokemonwebclient.service.stream_practise.slice;

import io.cherrytechnologies.pokemonwebclient.dto.PokemonDto;
import io.cherrytechnologies.pokemonwebclient.io.projections.PokemonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pokemon/slice")
public class SliceController {

    @Autowired
    public SliceService service;

    @GetMapping("/take-while/{id}")
    public ResponseEntity<List<PokemonDto>> takeWhilePokemonId(@PathVariable int id){
        return ResponseEntity.ok(service.getPokemonWhileId(id));
    }

    @GetMapping("/take-while/view/{id}")
    public ResponseEntity<List<PokemonView>> takeWhilePokemonViewId(@PathVariable int id){
        return ResponseEntity.ok(service.getPokemonViewWhileId(id));
    }
}
