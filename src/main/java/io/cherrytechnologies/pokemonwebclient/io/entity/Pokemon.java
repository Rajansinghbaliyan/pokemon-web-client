package io.cherrytechnologies.pokemonwebclient.io.entity;


import io.cherrytechnologies.pokemonwebclient.dto.PokemonDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@ToString
@NoArgsConstructor
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

    public PokemonDto pokemonToDto(Pokemon this){
        return PokemonDto
                .builder()
                .name(this.getName())
                .base_experience(this.getBase_experience())
                .id(this.getId())
                .weight(this.getWeight())
                .height(this.getHeight())
                .is_default(this.is_default())
                .location_area_encounters(this.getLocation_area_encounters())
                .species(this.getSpecies().speciesToDto())
                .abilities(this.abilities.stream().map(Ability::AblitityToDto).collect(Collectors.toSet()))
                .forms(this.forms.stream().map(Form::formToDto).collect(Collectors.toSet()))
                .moves(this.moves.stream().map(Move::moveToDto).collect(Collectors.toSet()))
                .stats(this.stats.stream().map(Stat::StatToDto).collect(Collectors.toSet()))
                .types(this.types.stream().map(Type::TypeToDto).collect(Collectors.toSet()))
                .build();
    }

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


