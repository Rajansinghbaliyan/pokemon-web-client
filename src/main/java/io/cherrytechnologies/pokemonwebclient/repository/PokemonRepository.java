package io.cherrytechnologies.pokemonwebclient.repository;

import io.cherrytechnologies.pokemonwebclient.domain.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, UUID> {
}
