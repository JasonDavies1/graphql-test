package com.amido.graphqltest.service;

import com.amido.graphqltest.domain.Player;

import java.util.List;

public interface PlayerService {

    List<Player> findAllPlayers();

    Player addNewPlayer(String username);

    Player findPlayerById(Integer id);

    Integer removePlayerByUsername(String username);

    Player updatePlayer(Player player);

}
