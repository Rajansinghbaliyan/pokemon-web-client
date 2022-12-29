package io.cherrytechnologies.pokemonwebclient.domain;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Sprites extends Base {
    public String back_default;
    public Object back_female;
    public String back_shiny;
    public Object back_shiny_female;
    public String front_default;
    public Object front_female;
    public String front_shiny;
    public Object front_shiny_female;
}
