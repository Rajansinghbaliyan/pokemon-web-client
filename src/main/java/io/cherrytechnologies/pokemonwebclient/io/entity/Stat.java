package io.cherrytechnologies.pokemonwebclient.io.entity;

import io.cherrytechnologies.pokemonwebclient.dto.StatDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Stat extends Base {
    public int base_stat;
    public int effort;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stat_id")
    public Stat2 stat;

    public StatDto StatToDto() {
        return StatDto
                .builder()
                .effort(this.getEffort())
                .name(this.getStat().getName())
                .url(this.getStat().getUrl())
                .base_stat(this.getBase_stat())
                .build();
    }
}
