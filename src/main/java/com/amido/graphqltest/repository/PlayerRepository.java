package com.amido.graphqltest.repository;

import com.amido.graphqltest.domain.Player;

import java.util.List;

public interface PlayerRepository {

    List<Player> findAllPlayers();

    Player addNewPlayer(String username);

    Player findPlayerById(Integer id);

}
