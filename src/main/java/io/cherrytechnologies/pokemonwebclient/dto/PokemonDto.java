package io.cherrytechnologies.pokemonwebclient.dto;


import io.cherrytechnologies.pokemonwebclient.io.entity.*;
import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@ToString
public class PokemonDto implements Serializable {
    public int id;
    public String name;
    public int base_experience;
    public int height;
    public boolean is_default;
    public String location_area_encounters;
    public int weight;
    public SpeciesDto species;
    public Set<AbilityDto> abilities;
    public Set<FormDto> forms;
    public Set<MoveDto> moves;
    public Set<StatDto> stats;
    public Set<TypeDto> types;

}
