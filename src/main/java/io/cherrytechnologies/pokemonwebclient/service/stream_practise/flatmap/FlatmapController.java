package io.cherrytechnologies.pokemonwebclient.service.stream_practise.flatmap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pokemon/flat-map")
public class FlatmapController {

    @Autowired
    private FlatmapService service;

    @GetMapping("/1")
    public ResponseEntity<List<List<Integer>>> questionOne(){
        return ResponseEntity.ok(service.questionOne());
    }

    @GetMapping("/types")
    public ResponseEntity<List<String>> getDifferentTypes(){
        return ResponseEntity.ok(service.differentTypesOfPokemon());
    }
}
