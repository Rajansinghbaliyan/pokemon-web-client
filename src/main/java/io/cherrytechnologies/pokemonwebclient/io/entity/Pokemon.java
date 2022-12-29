package io.cherrytechnologies.pokemonwebclient.io.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@ToString
public class Pokemon extends Base {
    public int id;
    public String name;
    public int base_experience;
    public int height;
    public boolean is_default;
    public String location_area_encounters;
    public int weight;
    @OneToOne(cascade = CascadeType.ALL)
    public Species species;
    @OneToMany(cascade = CascadeType.ALL)
    public Set<Ability> abilities;
    @OneToMany(cascade = CascadeType.ALL)
    public Set<Form> forms;
    @OneToMany(cascade = CascadeType.ALL)
    public Set<Move> moves;
    @OneToMany(cascade = CascadeType.ALL)
    public Set<Stat> stats;
    @OneToMany(cascade = CascadeType.ALL)
    public Set<Type> types;

//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }

}


