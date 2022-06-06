package com.amido.graphqltest.graphql.query;

import com.amido.graphqltest.domain.Item;
import com.amido.graphqltest.domain.Player;
import com.amido.graphqltest.repository.ItemRepository;
import com.amido.graphqltest.repository.PlayerRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class PlayerQuery implements GraphQLQueryResolver {

    private final PlayerRepository playerRepository;

    private final ItemRepository itemRepository;

    public List<Player> getAllPlayers() {
        return playerRepository.findAllPlayers();
    }

    public List<Item> getAllPlayerItems(final String userId) {
        return itemRepository.getPlayerInventoryItems(userId);
    }

}
