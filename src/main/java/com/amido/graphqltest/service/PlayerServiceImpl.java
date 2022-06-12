package com.amido.graphqltest.service;

import com.amido.graphqltest.domain.Player;
import com.amido.graphqltest.exception.PlayerNotFoundException;
import com.amido.graphqltest.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public List<Player> findAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player addNewPlayer(final String username) {
        return playerRepository.findByUsername(username)
                .orElseGet(() -> {
                    final Player newPlayer = new Player();
                    newPlayer.setLevel(1);
                    newPlayer.setUsername(username);
                    return playerRepository.save(newPlayer);
                });
    }

    @Override
    public Player findPlayerById(final Integer id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player with id " + id + " not found"));
    }

    @Override
    public Integer removePlayerByUsername(String username) {
        return playerRepository.findByUsername(username)
                .map(p -> {
                    int id = p.getId();
                    playerRepository.delete(p);
                    return id;
                })
                .orElseThrow(() -> new PlayerNotFoundException("Player with username " + username + " not found"));
    }

    @Override
    public Player updatePlayer(final Player player) {
        return playerRepository.save(player);
    }
}
