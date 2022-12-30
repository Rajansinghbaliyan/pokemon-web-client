package io.cherrytechnologies.pokemonwebclient.dto;

import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;

@Builder
@ToString
public class SpeciesDto implements Serializable {
    public String name;
    public String url;


}
