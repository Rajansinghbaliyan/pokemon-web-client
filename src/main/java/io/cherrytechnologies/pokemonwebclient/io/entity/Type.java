package io.cherrytechnologies.pokemonwebclient.io.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Type extends Base{ public int slot;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id")
    public Type2 type;
}
