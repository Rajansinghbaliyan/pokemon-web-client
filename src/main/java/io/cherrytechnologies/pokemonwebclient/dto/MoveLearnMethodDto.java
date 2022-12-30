package io.cherrytechnologies.pokemonwebclient.dto;

import io.cherrytechnologies.pokemonwebclient.io.entity.MoveLearnMethod;
import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;

@Builder
@ToString
public class MoveLearnMethodDto implements Serializable {
    public String name;
    public String url;


}
