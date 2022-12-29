package io.cherrytechnologies.pokemonwebclient.io.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Stat extends Base { public int base_stat;
    public int effort;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stat_id")
    public Stat2 stat;
}
