package com.amido.graphqltest.util;

import com.amido.graphqltest.domain.Item;
import com.amido.graphqltest.domain.MarketplaceListing;
import com.amido.graphqltest.domain.Player;

import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class DomainExampleHelper {

    public static Player testPlayer(final Item... inventoryItems) {
        final Player player = new Player();
        player.setId(1);
        player.setLevel(12);
        player.setUsername("coolguy42");
        player.setInventory(Arrays.stream(inventoryItems).collect(Collectors.toList()));
        return player;
    }

    public static Player testPlayerWithCurrency(final int balance, final Item... items) {
        final Player player = testPlayer(items);
        player.setCurrency(balance);
        return player;
    }

    public static Item redPotion() {
        final Item item = new Item();
        item.setId(1);
        item.setName("Red Potion");
        return item;
    }

    public static MarketplaceListing testMarketplaceListingForRedPotion(final String id) {
        final MarketplaceListing marketplaceListing = new MarketplaceListing();
        marketplaceListing.setId(id);
        marketplaceListing.setSeller(testPlayer());
        marketplaceListing.setItem(redPotion());
        marketplaceListing.setRequestedPrice(200);
        return marketplaceListing;
    }

}
