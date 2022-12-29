package io.cherrytechnologies.pokemonwebclient.io.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Move extends Base {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "move_id")
    public Move2 move;

    public Move2 getMove() {
        return move;
    }

    public void setMove(Move2 move) {
        this.move = move;
    }
}
