package com.amido.graphqltest.service;

import com.amido.graphqltest.domain.Item;
import com.amido.graphqltest.domain.MarketplaceListing;
import com.amido.graphqltest.domain.Player;
import com.amido.graphqltest.exception.ItemNotFoundException;
import com.amido.graphqltest.exception.ListingNotFoundException;
import com.amido.graphqltest.repository.MarketplaceListingRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class MarketplaceListingServiceImpl implements MarketplaceListingService {

    private final Supplier<UUID> uuidSupplier;
    private final PlayerService playerService;
    private final MarketplaceListingRepository marketplaceListingRepository;

    @Override
    public MarketplaceListing addMarketplaceListing(
            final int playerId,
            final int itemId,
            final int price) {
        final Player seller = playerService.findPlayerById(playerId);
        final Item item = seller.getInventory().stream()
                .filter(i -> i.getId() == itemId)
                .findFirst()
                .orElseThrow(() -> new ItemNotFoundException(
                        String.format("Player %s does not have item with ID: %d",
                                seller.getUsername(),
                                itemId)));
        seller.getInventory().remove(item);
        playerService.updatePlayer(seller);
        return marketplaceListingRepository.save(toListing(price, seller).apply(item));
    }

    @Override
    public MarketplaceListing findMarketplaceListingById(final String id) {
        return marketplaceListingRepository.findById(id)
                .orElseThrow(() -> new ListingNotFoundException("Listing with ID: " + id + " not found on marketplace."));
    }

    @NotNull
    private Function<Item, MarketplaceListing> toListing(
            final int price,
            final Player seller) {
        return item -> {
            final MarketplaceListing listing = new MarketplaceListing();
            listing.setId(uuidSupplier.get().toString());
            listing.setItem(item);
            listing.setSeller(seller);
            listing.setStatus("For Sale");
            listing.setRequestedPrice(price);
            return listing;
        };
    }
}
