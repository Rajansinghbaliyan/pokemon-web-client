package io.cherrytechnologies.pokemonwebclient.io.entity;

import io.cherrytechnologies.pokemonwebclient.dto.TypeDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Type extends Base {
    public int slot;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id")
    public Type2 type;

    public TypeDto TypeToDto(){
        return TypeDto
                .builder()
                .name(this.getType().getName())
                .url(this.getType().getUrl())
                .slot(this.getSlot())
                .build();
    }
}
