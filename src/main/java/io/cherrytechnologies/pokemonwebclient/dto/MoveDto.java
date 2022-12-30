package io.cherrytechnologies.pokemonwebclient.dto;

import io.cherrytechnologies.pokemonwebclient.io.entity.Move;
import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;

@Builder
@ToString
public class MoveDto implements Serializable {
    public String name;
    public String url;


}
