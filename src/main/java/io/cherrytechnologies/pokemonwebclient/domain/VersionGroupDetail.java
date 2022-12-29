package io.cherrytechnologies.pokemonwebclient.domain;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class VersionGroupDetail extends Base {
    public int level_learned_at;
    public MoveLearnMethod move_learn_method;
    public VersionGroup version_group;
}
