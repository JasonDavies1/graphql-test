package com.amido.graphqltest.repository;

import com.amido.graphqltest.domain.Item;
import com.amido.graphqltest.domain.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {

    private final PlayerRepository playerRepository;

    @Override
    public List<Item> getPlayerInventoryItems(final Integer userId) {
        final Player player = playerRepository.findPlayerById(userId);
        return player.getInventory();
    }
}
