package io.cherrytechnologies.pokemonwebclient.dto;

import io.cherrytechnologies.pokemonwebclient.io.entity.Item;
import lombok.Builder;
import lombok.ToString;

import java.awt.*;
import java.io.Serializable;

@Builder
@ToString
public class ItemDto implements Serializable {
    public String name;
    public String url;



}
