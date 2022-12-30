package io.cherrytechnologies.pokemonwebclient.io.repository;

import io.cherrytechnologies.pokemonwebclient.io.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.TreeSet;
import java.util.UUID;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, UUID> {
    Optional<Pokemon> getPokemonById(int id);

    TreeSet<Pokemon> getPokemonsByIdBetween(int start, int end);

//    <T> List<T> findBy(Class<T> type);
}
