package com.amido.graphqltest.service;

import com.amido.graphqltest.domain.Item;
import com.amido.graphqltest.domain.Player;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ItemServiceImplTest {

    private final PlayerService playerService = mock(PlayerService.class);
    private final ItemService itemService = new ItemServiceImpl(playerService);

    @Test
    public void givenAPlayerIsFoundById_WhenRetrievingItemsForPlayer_ThenListOfItemsAreReturned() {
        final Integer playerID = 1;
        final Player player = new Player();
        final Item item = new Item();
        item.setName("Red Potion");
        player.setId(playerID);
        player.setInventory(Collections.singletonList(item));

        given(playerService.findPlayerById(playerID))
                .willReturn(player);

        final List<Item> result = itemService.getPlayerInventoryItems(playerID);

        assertThat(result)
                .singleElement()
                .satisfies(i -> assertThat(i.getName())
                        .isEqualTo("Red Potion"));
    }

    @Test
    public void givenPlayerIsFoundById_WhenRetrievingItemsForPlayer_ThenAnEmptyListIsReturned() {
        final Player player = new Player();
        final Integer playerId = 1;
        player.setId(playerId);

        given(playerService.findPlayerById(playerId))
                .willReturn(player);

        final List<Item> result = itemService.getPlayerInventoryItems(playerId);

        assertThat(result)
                .isEmpty();
    }

}