package io.cherrytechnologies.pokemonwebclient.io.entity;

import io.cherrytechnologies.pokemonwebclient.dto.SpeciesDto;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Species extends Base {
    public String name;
    public String url;

    public SpeciesDto speciesToDto(){
        return SpeciesDto
                .builder()
                .name(this.getUrl())
                .url(this.getName())
                .build();
    }
}
