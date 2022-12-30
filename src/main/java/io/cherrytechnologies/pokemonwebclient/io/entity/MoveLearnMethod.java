package io.cherrytechnologies.pokemonwebclient.io.entity;


import io.cherrytechnologies.pokemonwebclient.dto.MoveLearnMethodDto;
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

    public MoveLearnMethodDto moveLearnMethodToDto(){
        return MoveLearnMethodDto
                .builder()
                .name(this.getName())
                .url(this.getUrl())
                .build();
    }
}
