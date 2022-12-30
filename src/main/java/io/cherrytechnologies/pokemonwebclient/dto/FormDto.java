package io.cherrytechnologies.pokemonwebclient.dto;

import io.cherrytechnologies.pokemonwebclient.io.entity.Form;
import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;

@Builder
@ToString
public class FormDto implements Serializable {
    public String name;
    public String url;


}
