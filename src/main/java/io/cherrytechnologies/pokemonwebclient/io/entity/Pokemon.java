package io.cherrytechnologies.pokemonwebclient.io.entity;


import io.cherrytechnologies.pokemonwebclient.dto.PokemonDto;
import io.cherrytechnologies.pokemonwebclient.io.projections.PokemonView;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@ToString
@NoArgsConstructor
public class Pokemon extends Base implements Comparable<Pokemon> {
    private int id;
    private String name;
    private int base_experience;
    private int height;
    private boolean is_default;
    private String location_area_encounters;
    private int weight;
    @OneToOne(cascade = CascadeType.ALL)
    private Species species;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "abilities_pokemon",
            joinColumns = @JoinColumn(name = "pokemon_uuid"),
            inverseJoinColumns = @JoinColumn(name = "ability_uuid"))
    private Set<Ability> abilities;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Form> forms;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Move> moves;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Stat> stats;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Type> types;

    public PokemonDto pokemonToDto(Pokemon this) {
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

    public PokemonView pokemonToPokemonView() {
        return PokemonView
                .builder()
                .weight(this.getWeight())
                .id(this.getId())
                .name(this.getName())
                .height(this.getHeight())
                .base_experience(this.base_experience)
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


