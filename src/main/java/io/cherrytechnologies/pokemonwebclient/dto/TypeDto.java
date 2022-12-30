package io.cherrytechnologies.pokemonwebclient.dto;

import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;

@Builder
@ToString
public class TypeDto implements Serializable {
    public int slot;
    public String name;
    public String url;



}
