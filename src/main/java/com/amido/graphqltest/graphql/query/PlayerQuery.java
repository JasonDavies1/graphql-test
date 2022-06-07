package com.amido.graphqltest.graphql.query;

import com.amido.graphqltest.domain.Item;
import com.amido.graphqltest.domain.Player;
import com.amido.graphqltest.service.ItemService;
import com.amido.graphqltest.service.PlayerService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class PlayerQuery implements GraphQLQueryResolver {

    private final PlayerService playerService;

    private final ItemService itemService;

    public List<Player> getAllPlayers() {
        return playerService.findAllPlayers();
    }

    public List<Item> getAllPlayerItems(final Integer userId) {
        return itemService.getPlayerInventoryItems(userId);
    }

}
