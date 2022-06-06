package com.amido.graphqltest.repository;

import com.amido.graphqltest.domain.Item;
import com.amido.graphqltest.domain.Player;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ItemRepositoryImplTest {

    private final PlayerRepository playerRepository = mock(PlayerRepository.class);
    private final ItemRepository itemRepository = new ItemRepositoryImpl(playerRepository);

    @Test
    public void givenAPlayerIsFoundById_WhenRetrievingItemsForPlayer_ThenListOfItemsAreReturned() {
        final String playerID = "1";
        final Player player = new Player();
        final Item item = new Item();
        item.setName("Red Potion");
        player.setId(playerID);
        player.setInventory(Collections.singletonList(item));

        given(playerRepository.findPlayerById(playerID))
                .willReturn(player);

        final List<Item> result = itemRepository.getPlayerInventoryItems(playerID);

        assertThat(result)
                .singleElement()
                .satisfies(i -> assertThat(i.getName())
                        .isEqualTo("Red Potion"));
    }

    @Test
    public void givenPlayerIsFoundById_WhenRetrievingItemsForPlayer_ThenAnEmptyListIsReturned() {
        final Player player = new Player();
        final String playerId = "1";
        player.setId(playerId);

        given(playerRepository.findPlayerById(playerId))
                .willReturn(player);

        final List<Item> result = itemRepository.getPlayerInventoryItems(playerId);

        assertThat(result)
                .isEmpty();
    }

}