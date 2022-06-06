package com.amido.graphqltest.graphql.query;

import com.amido.graphqltest.domain.Item;
import com.amido.graphqltest.domain.Player;
import com.amido.graphqltest.graphql.mutation.PlayerMutation;
import com.amido.graphqltest.graphql.resolver.PlayerResolver;
import com.amido.graphqltest.repository.ItemRepository;
import com.amido.graphqltest.repository.PlayerRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class PlayerQueryTest {

    private final PlayerRepository playerRepository = mock(PlayerRepository.class);
    private final ItemRepository itemRepository = mock(ItemRepository.class);

    private final PlayerQuery playerQuery = new PlayerQuery(playerRepository, itemRepository);

    @Test
    public void givenAllPlayersAreRequested_WhenSearchingForAllPlayers_ThenThisActionIsPerformedUsingThePlayerRepository(){
        final List<Player> result = playerQuery.getAllPlayers();

        then(playerRepository)
                .should()
                .findAllPlayers();
    }
    
    @Test
    public void givenIndividualPlayerInventoryIsRequested_WhenSearchingForInventory_ThenThisIsDoneThroughTheItemRepository(){
        final List<Item> result = playerQuery.getAllPlayerItems("1");

        then(itemRepository)
                .should()
                .getPlayerInventoryItems("1");
    }

}