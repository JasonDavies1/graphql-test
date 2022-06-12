package com.amido.graphqltest.service;

import com.amido.graphqltest.domain.Item;
import com.amido.graphqltest.domain.MarketplaceListing;
import com.amido.graphqltest.domain.Player;
import com.amido.graphqltest.exception.ItemNotFoundException;
import com.amido.graphqltest.exception.ListingNotFoundException;
import com.amido.graphqltest.exception.PlayerNotFoundException;
import com.amido.graphqltest.repository.MarketplaceListingRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import static com.amido.graphqltest.util.DomainExampleHelper.redPotion;
import static com.amido.graphqltest.util.DomainExampleHelper.testPlayer;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;

class MarketplaceListingServiceImplTest {

    private final Supplier<UUID> uuidSupplier = mock(Supplier.class);
    final PlayerService playerService = mock(PlayerServiceImpl.class);
    final MarketplaceListingRepository marketplaceListingRepository = mock(MarketplaceListingRepository.class);
    final MarketplaceListingService marketplaceListingService = new MarketplaceListingServiceImpl(
            uuidSupplier,
            playerService,
            marketplaceListingRepository);

    @Test
    public void givenPlayerHasItemInInventory_WhenPlayerRequestsItemToBeListedOnMarketplace_ThenANewMarketplaceListingIsSaved() {
        final UUID id = UUID.randomUUID();
        final Item itemToSell = redPotion();
        final Player seller = testPlayer(itemToSell);
        given(playerService.findPlayerById(1))
                .willReturn(seller);
        given(uuidSupplier.get()).willReturn(id);

        MarketplaceListing expectedListing = new MarketplaceListing();
        expectedListing.setId(id.toString());
        expectedListing.setItem(itemToSell);
        expectedListing.setRequestedPrice(200);
        expectedListing.setSeller(seller);
        expectedListing.setStatus("For Sale");

        final MarketplaceListing result = marketplaceListingService.addMarketplaceListing(1, 1, 200);

        then(marketplaceListingRepository)
                .should()
                .save(refEq(expectedListing));
    }

    @Test
    public void givenPlayerDoesNotHaveItemInInventory_WhenListingItemOnMarketplace_ThenListingShouldNotBeSaved() {
        final Player seller = testPlayer();

        given(playerService.findPlayerById(1))
                .willReturn(seller);

        assertThatCode(() -> marketplaceListingService.addMarketplaceListing(1, 1, 200))
                .isInstanceOf(ItemNotFoundException.class)
                .hasMessage("Player coolguy42 does not have item with ID: 1");

        then(marketplaceListingRepository)
                .should(never())
                .save(any());
    }

    @Test
    public void givenPlayerNotFoundById_WhenCreatingMarketplaceListing_ThenMarketplaceListingWillNotBeCreated() {
        given(playerService.findPlayerById(1))
                .willThrow(new PlayerNotFoundException("Player with id 1 not found"));

        assertThatCode(() -> marketplaceListingService.addMarketplaceListing(1, 1, 200))
                .isInstanceOf(PlayerNotFoundException.class)
                .hasMessage("Player with id 1 not found");

        then(marketplaceListingRepository)
                .should(never())
                .save(any());
    }

    @Test
    public void givenMarketplaceListingDoesNotExist_WhenDeletingListing_ThenRepositoryShouldDeleteNothing() {
        given(marketplaceListingRepository.findById("aaaaa"))
                .willThrow(new ListingNotFoundException("Listing with ID: aaaaa not found on marketplace."));

        assertThatCode(() -> marketplaceListingService.deleteMarketplaceListingById("aaaaa"))
                .isInstanceOf(ListingNotFoundException.class);

        then(marketplaceListingRepository)
                .should(never())
                .delete(any());
    }

    @Test
    public void givenMarketplaceListingExists_WhenDeletingListing_ThenRepositoryShouldDeleteTheListing() {
        final MarketplaceListing listing = new MarketplaceListing();
        given(marketplaceListingRepository.findById("aaaaa"))
                .willReturn(Optional.of(listing));

        marketplaceListingService.deleteMarketplaceListingById("aaaaa");

        then(marketplaceListingRepository)
                .should()
                .delete(listing);
    }
}