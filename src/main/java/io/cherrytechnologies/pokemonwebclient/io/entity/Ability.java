package io.cherrytechnologies.pokemonwebclient.io.entity;


import io.cherrytechnologies.pokemonwebclient.dto.AbilityDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Ability extends Base {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ability_id")
    public Ability2 ability;
    public boolean is_hidden;
    public int slot;

    public AbilityDto AblitityToDto(Ability this){
        return AbilityDto
                .builder()
                .is_hidden(this.is_hidden)
                .name(this.ability.name)
                .slot(this.slot)
                .url(this.getAbility().getUrl())
                .build();
    }
}
