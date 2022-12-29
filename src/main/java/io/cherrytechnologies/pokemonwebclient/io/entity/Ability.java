package io.cherrytechnologies.pokemonwebclient.io.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Ability extends Base {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ability_id")
    public Ability2 ability;
    public boolean is_hidden;
    public int slot;
}
