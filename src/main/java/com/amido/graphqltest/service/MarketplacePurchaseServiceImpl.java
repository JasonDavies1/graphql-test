package com.amido.graphqltest.service;

import com.amido.graphqltest.domain.Item;
import com.amido.graphqltest.domain.MarketplaceListing;
import com.amido.graphqltest.domain.MarketplaceReceipt;
import com.amido.graphqltest.domain.Player;
import com.amido.graphqltest.domain.dto.ItemDto;
import com.amido.graphqltest.domain.dto.PlayerDto;
import com.amido.graphqltest.exception.ItemAlreadyOwnedException;
import com.amido.graphqltest.exception.NotEnoughCurrencyException;
import com.amido.graphqltest.repository.MarketplaceReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Clock;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MarketplacePurchaseServiceImpl implements MarketplacePurchaseService {

    private final Clock clock;
    private final PlayerService playerService;
    private final MarketplaceListingService marketplaceListingService;

    private final MarketplaceReceiptRepository marketplaceReceiptRepository;

    @Override
    public MarketplaceReceipt buyItem(
            final String listingId,
            final int buyerId) {
        final MarketplaceListing listing = marketplaceListingService.findMarketplaceListingById(listingId);
        final Player buyer = playerService.findPlayerById(buyerId);
        checkBuyerDoesNotOwnItem(buyer, listing.getItem());
        final int buyerBalanceAfterPurchase = getBalanceAfterPurchase(buyer, listing.getRequestedPrice());
        final Player updatedBuyer = updateBuyer(buyer, buyerBalanceAfterPurchase, listing.getItem());
        final Player seller = listing.getSeller();
        final Player updatedSeller = updateSeller(seller, listing.getRequestedPrice());
        marketplaceListingService.deleteMarketplaceListingById(listingId);
        final MarketplaceReceipt receipt = createReceipt(listing, updatedBuyer, updatedSeller);
        return marketplaceReceiptRepository.save(receipt);
    }

    private void checkBuyerDoesNotOwnItem(final Player buyer, final Item item) {
        final boolean buyerOwnsItem = buyer.getInventory().stream()
                .anyMatch(i -> i.getId() == item.getId());
        if (buyerOwnsItem) {
            throw new ItemAlreadyOwnedException("You already own this item and cannot buy another");
        }
    }

    private int getBalanceAfterPurchase(
            final Player buyer,
            final int requestedPrice) {
        final int balanceAfterPurchase = buyer.getCurrency() - requestedPrice;
        if (balanceAfterPurchase < 0) {
            throw new NotEnoughCurrencyException("Insufficient balance to purchase listing");
        }
        return balanceAfterPurchase;
    }

    private Player updateBuyer(
            final Player buyer,
            final int balanceAfterPurchase,
            final Item purchasedItem) {
        buyer.setCurrency(balanceAfterPurchase);
        buyer.getInventory().add(purchasedItem);
        return playerService.updatePlayer(buyer);
    }

    private Player updateSeller(
            final Player seller,
            final int listingPrice) {
        seller.setCurrency(seller.getCurrency() + listingPrice);
        return playerService.updatePlayer(seller);
    }

    private MarketplaceReceipt createReceipt(
            final MarketplaceListing listing,
            final Player buyer,
            final Player seller) {
        MarketplaceReceipt marketplaceReceipt = new MarketplaceReceipt();
        marketplaceReceipt.setId(UUID.randomUUID().toString());
        marketplaceReceipt.setBuyer(PlayerDto.from(buyer));
        marketplaceReceipt.setSeller(PlayerDto.from(seller));
        marketplaceReceipt.setItem(ItemDto.from(listing.getItem()));
        marketplaceReceipt.setSellPrice(listing.getRequestedPrice());
        final Instant now = Instant.now(clock);
        marketplaceReceipt.setTransactionDate(Date.from(now));
        return marketplaceReceipt;
    }
}
