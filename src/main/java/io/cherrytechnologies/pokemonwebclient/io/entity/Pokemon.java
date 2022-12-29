package io.cherrytechnologies.pokemonwebclient.io.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.ToString;

import java.util.Objects;
import java.util.Set;

@Entity
@Data
@ToString
public class Pokemon extends Base implements Comparable<Pokemon> {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pokemon pokemon = (Pokemon) o;
        return id == pokemon.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public int compareTo(Pokemon o) {
        return Integer.compare(this.id, o.id);
    }
}


