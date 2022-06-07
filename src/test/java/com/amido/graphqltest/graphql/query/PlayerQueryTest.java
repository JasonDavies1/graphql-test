package com.amido.graphqltest.graphql.query;

import com.amido.graphqltest.domain.Item;
import com.amido.graphqltest.domain.Player;
import com.amido.graphqltest.service.ItemService;
import com.amido.graphqltest.service.PlayerService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class PlayerQueryTest {

    private final PlayerService playerService = mock(PlayerService.class);
    private final ItemService itemService = mock(ItemService.class);

    private final PlayerQuery playerQuery = new PlayerQuery(playerService, itemService);

    @Test
    public void givenAllPlayersAreRequested_WhenSearchingForAllPlayers_ThenThisActionIsPerformedUsingThePlayerRepository() {
        final List<Player> result = playerQuery.getAllPlayers();

        then(playerService)
                .should()
                .findAllPlayers();
    }

    @Test
    public void givenIndividualPlayerInventoryIsRequested_WhenSearchingForInventory_ThenThisIsDoneThroughTheItemRepository() {
        final List<Item> result = playerQuery.getAllPlayerItems(1);

        then(itemService)
                .should()
                .getPlayerInventoryItems(1);
    }

}