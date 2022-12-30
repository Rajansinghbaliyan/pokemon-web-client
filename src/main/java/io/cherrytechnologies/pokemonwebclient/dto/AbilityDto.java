package io.cherrytechnologies.pokemonwebclient.dto;

import io.cherrytechnologies.pokemonwebclient.io.entity.Ability;
import io.cherrytechnologies.pokemonwebclient.io.entity.Ability2;
import lombok.Builder;
import lombok.Data;

import java.awt.*;
import java.io.Serializable;

@Builder
@Data
public class AbilityDto implements Serializable {
    public boolean is_hidden;
    public int slot;
    public String name;
    public String url;


}
