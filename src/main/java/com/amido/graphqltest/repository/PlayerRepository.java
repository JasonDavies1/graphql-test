package com.amido.graphqltest.repository;

import com.amido.graphqltest.domain.Player;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    @NotNull
    Optional<Player> findById(@NotNull Integer id);

    Optional<Player> findByUsername(@NotNull String username);

}
