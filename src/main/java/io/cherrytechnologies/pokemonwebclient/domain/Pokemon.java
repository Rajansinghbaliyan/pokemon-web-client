package io.cherrytechnologies.pokemonwebclient.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@NoArgsConstructor
@Entity
public class Pokemon extends Base {

    @OneToMany(cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    public HashSet<Ability> abilities;
    public int base_experience;

    @OneToMany(cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    public HashSet<Form> forms;
    public int height;
    public int id;
    public boolean is_default;
    public String location_area_encounters;

    @OneToMany(cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    public HashSet<Move> moves;

    public String name;
    public int order;

    @OneToOne
    public Species species;

    @OneToOne
    public Sprites sprites;

    @OneToMany(cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    public HashSet<Stat> stats;

    @OneToMany(cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    public HashSet<Type> types;
    public int weight;
}


