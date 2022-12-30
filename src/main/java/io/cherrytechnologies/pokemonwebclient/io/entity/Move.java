package io.cherrytechnologies.pokemonwebclient.io.entity;

import io.cherrytechnologies.pokemonwebclient.dto.MoveDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Move extends Base {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "move_id")
    public Move2 move;

    public MoveDto moveToDto(Move this){
        return MoveDto
                .builder()
                .name(this.getMove().getName())
                .url(this.getMove().getUrl())
                .build();
    }
}
