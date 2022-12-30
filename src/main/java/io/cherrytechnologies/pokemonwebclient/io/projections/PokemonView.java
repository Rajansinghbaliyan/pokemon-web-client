package io.cherrytechnologies.pokemonwebclient.io.projections;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class PokemonView implements Serializable {
    int id;
    String name;
    int base_experience;
    int height;
    int weight;
}
