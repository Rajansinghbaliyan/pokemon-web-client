package io.cherrytechnologies.pokemonwebclient.io.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
@Data
@NoArgsConstructor
public class Base implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    public UUID uuid;

    @Version
    public Long version;

    @CreationTimestamp
    @Column(updatable = false)
    public Timestamp createdDate;

    @UpdateTimestamp
    public Timestamp lastModifiedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Base base = (Base) o;
        return uuid.equals(base.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
