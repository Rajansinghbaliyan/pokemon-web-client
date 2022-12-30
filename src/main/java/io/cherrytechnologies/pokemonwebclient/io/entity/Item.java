package io.cherrytechnologies.pokemonwebclient.io.entity;


import io.cherrytechnologies.pokemonwebclient.dto.ItemDto;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Item extends Base {
    public String name;
    public String url;

    public ItemDto itemToDto() {
        return ItemDto
                .builder()
                .name(this.getName())
                .url(this.getUrl())
                .build();
    }
}
