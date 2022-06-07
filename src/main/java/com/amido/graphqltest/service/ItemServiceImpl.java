package com.amido.graphqltest.service;

import com.amido.graphqltest.domain.Item;
import com.amido.graphqltest.domain.Player;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final PlayerService playerService;

    @Override
    public List<Item> getPlayerInventoryItems(final Integer userId) {
        final Player player = playerService.findPlayerById(userId);
        return player.getInventory();
    }
}
