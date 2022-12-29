package io.cherrytechnologies.pokemonwebclient.domain;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class MoveLearnMethod extends Base {
    public String name;
    public String url;
}
