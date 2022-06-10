package com.amido.graphqltest.service;

import com.amido.graphqltest.domain.Item;
import com.amido.graphqltest.domain.MarketplaceListing;
import com.amido.graphqltest.domain.MarketplaceReceipt;
import com.amido.graphqltest.domain.Player;
import com.amido.graphqltest.exception.ItemAlreadyOwnedException;
import com.amido.graphqltest.exception.ListingNotFoundException;
import com.amido.graphqltest.exception.NotEnoughCurrencyException;
import com.amido.graphqltest.exception.PlayerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.amido.graphqltest.util.DomainExampleHelper.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

class MarketplacePurchaseServiceImplTest {

    private final static String LISTING_ID = UUID.randomUUID().toString();
    private final Clock clock = mock(Clock.class);
    private final PlayerService playerService = mock(PlayerService.class);
    private final MarketplaceListingService marketplaceListingService = mock(MarketplaceListingService.class);
    private final MarketplacePurchaseServiceImpl marketplacePurchaseService = new MarketplacePurchaseServiceImpl(
            clock,
            playerService,
            marketplaceListingService);

    @BeforeEach
    public void setUp() {
        given(clock.instant())
                .willReturn(Instant.now());
    }

    @Test
    public void givenMarketPlaceListingNotFoundById_WhenPurchasingItem_ThenSellerShouldNotBeRetrieved() {
        given(marketplaceListingService.findMarketplaceListingById(LISTING_ID))
                .willThrow(new ListingNotFoundException("Listing with ID: " + LISTING_ID + " not found on marketplace."));

        assertThatCode(() -> marketplacePurchaseService.buyItem(LISTING_ID, 2))
                .isInstanceOf(ListingNotFoundException.class)
                .hasMessage("Listing with ID: " + LISTING_ID + " not found on marketplace.");

        verifyNoInteractions(playerService);
    }

    @Test
    public void givenBuyerIsNotFoundById_WhenPurchasingItem_ThenExceptionShouldBeThrown() {
        final int buyerId = 2;
        given(marketplaceListingService.findMarketplaceListingById(LISTING_ID))
                .willReturn(new MarketplaceListing());
        given(playerService.findPlayerById(buyerId))
                .willThrow(new PlayerNotFoundException("Player with id 2 not found"));

        assertThatCode(() -> marketplacePurchaseService.buyItem(LISTING_ID, buyerId))
                .isInstanceOf(PlayerNotFoundException.class)
                .hasMessage("Player with id 2 not found");
    }

    @Test
    public void givenListingAndBuyerAreFoundButBuyerDoesNotHaveEnoughCurrencyToBuy_WhenPurchasingItem_ThenNeitherPlayerShouldBeUpdated() {
        final int buyerId = 2;
        given(marketplaceListingService.findMarketplaceListingById(LISTING_ID))
                .willReturn(testMarketplaceListingForRedPotion(LISTING_ID));
        given(playerService.findPlayerById(buyerId))
                .willReturn(buyer(100));

        assertThatCode(() -> marketplacePurchaseService.buyItem(LISTING_ID, buyerId))
                .isInstanceOf(NotEnoughCurrencyException.class)
                .hasMessage("Insufficient balance to purchase listing");

        then(playerService)
                .should(never())
                .updatePlayer(any());
    }

    @Test
    public void givenBuyerAlreadyOwnsItem_WhenPurchasingItem_ThenNeitherPlayerShouldBeUpdated() {
        final int buyerId = 2;
        given(marketplaceListingService.findMarketplaceListingById(LISTING_ID))
                .willReturn(testMarketplaceListingForRedPotion(LISTING_ID));
        given(playerService.findPlayerById(buyerId))
                .willReturn(buyer(200, redPotion()));

        assertThatCode(() -> marketplacePurchaseService.buyItem(LISTING_ID, buyerId))
                .isInstanceOf(ItemAlreadyOwnedException.class)
                .hasMessage("You already own this item and cannot buy another");

        then(playerService)
                .should(never())
                .updatePlayer(any());
    }

    @Test
    public void givenPurchaseIsSuccessful_WhenPurchasingItem_ThenBuyerShouldHaveItemAndReducedCurrencyInReceipt() {
        final int buyerId = 2;
        final Player buyer = buyer(200);
        final MarketplaceListing listing = testMarketplaceListingForRedPotion(LISTING_ID);
        final Player seller = listing.getSeller();
        given(marketplaceListingService.findMarketplaceListingById(LISTING_ID))
                .willReturn(listing);
        given(playerService.findPlayerById(buyerId))
                .willReturn(buyer);
        given(playerService.updatePlayer(seller))
                .willReturn(testPlayerWithCurrency(200));
        given(playerService.updatePlayer(buyer))
                .willReturn(buyer(0, redPotion()));

        final MarketplaceReceipt result = marketplacePurchaseService.buyItem(LISTING_ID, buyerId);

        assertThat(result.getBuyer().getInventory())
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.getName())
                            .isEqualTo("Red Potion");
                });
        assertThat(result.getBuyer().getCurrency())
                .isEqualTo(0);
    }

    @Test
    public void givenPurchaseIsSuccessful_WhenPurchasingItem_ThenSellerShouldHaveIncreasedCurrencyInReceipt() {
        final int buyerId = 2;
        final Player buyer = buyer(200);
        final MarketplaceListing listing = testMarketplaceListingForRedPotion(LISTING_ID);
        final Player seller = listing.getSeller();
        given(marketplaceListingService.findMarketplaceListingById(LISTING_ID))
                .willReturn(listing);
        given(playerService.findPlayerById(buyerId))
                .willReturn(buyer);
        given(playerService.updatePlayer(seller))
                .willReturn(testPlayerWithCurrency(200));
        given(playerService.updatePlayer(buyer))
                .willReturn(buyer(0, redPotion()));

        final MarketplaceReceipt result = marketplacePurchaseService.buyItem(LISTING_ID, buyerId);

        assertThat(result.getSeller().getCurrency())
                .isEqualTo(200);
    }

    @Test
    public void givenPurchaseIsSuccessful_WhenPurchasingItem_ThenListingShouldBeDeletedFromMarketplace() {
        final int buyerId = 2;
        final Player buyer = buyer(200);
        final MarketplaceListing listing = testMarketplaceListingForRedPotion(LISTING_ID);
        final Player seller = listing.getSeller();
        given(marketplaceListingService.findMarketplaceListingById(LISTING_ID))
                .willReturn(listing);
        given(playerService.findPlayerById(buyerId))
                .willReturn(buyer);
        given(playerService.updatePlayer(seller))
                .willReturn(testPlayerWithCurrency(200));
        given(playerService.updatePlayer(buyer))
                .willReturn(buyer(0, redPotion()));

        final MarketplaceReceipt result = marketplacePurchaseService.buyItem(LISTING_ID, buyerId);

        then(marketplaceListingService)
                .should()
                .deleteMarketplaceListingById(LISTING_ID);
    }

    private Player buyer(
            final int balance,
            final Item... items) {
        final Player buyer = new Player();
        buyer.setId(2);
        buyer.setCurrency(balance);
        buyer.setInventory(Arrays.stream(items).collect(Collectors.toList()));
        return buyer;
    }

}