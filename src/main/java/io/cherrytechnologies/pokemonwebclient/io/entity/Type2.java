package io.cherrytechnologies.pokemonwebclient.io.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Type2 extends Base {
    public String name;
    public String url;
}
