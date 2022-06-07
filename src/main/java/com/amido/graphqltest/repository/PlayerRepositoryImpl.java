package com.amido.graphqltest.repository;

import com.amido.graphqltest.domain.Player;
import com.amido.graphqltest.exception.PlayerNotFoundException;

import java.util.List;

public class PlayerRepositoryImpl implements PlayerRepository {

    private final List<Player> players;

    public PlayerRepositoryImpl(final List<Player> players) {
        this.players = players;
    }

    @Override
    public List<Player> findAllPlayers() {
        return players;
    }

    @Override
    public Player addNewPlayer(final String username) {
        return players.stream()
                .filter(p -> p.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElseGet(() -> {
                    final Player newPlayer = new Player();
                    newPlayer.setLevel(1);
                    newPlayer.setUsername(username);
                    players.add(newPlayer);
                    return newPlayer;
                });

    }

    @Override
    public Player findPlayerById(final Integer id) {
        return players.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new PlayerNotFoundException("Player with id " + id + " not found"));
    }
}
