package io.cherrytechnologies.pokemonwebclient.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Move extends Base {
    @OneToOne
    public Move move;

    @OneToMany
    public ArrayList<VersionGroupDetail> version_group_details;
}
