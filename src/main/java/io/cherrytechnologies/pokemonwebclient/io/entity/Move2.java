package io.cherrytechnologies.pokemonwebclient.io.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Move2 extends Base {
    public String name;
    public String url;
}
