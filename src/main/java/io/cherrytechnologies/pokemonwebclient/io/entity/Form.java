package io.cherrytechnologies.pokemonwebclient.io.entity;


import io.cherrytechnologies.pokemonwebclient.dto.FormDto;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Form extends Base {
    public String name;
    public String url;

    public FormDto formToDto(){
        return FormDto
                .builder()
                .name(this.getName())
                .url(this.getUrl())
                .build();
    }
}
