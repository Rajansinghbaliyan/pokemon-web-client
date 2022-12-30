package io.cherrytechnologies.pokemonwebclient.dto;

import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;

@Builder
@ToString
public class StatDto implements Serializable {
    public int base_stat;
    public int effort;
    public String name;
    public String url;


}
