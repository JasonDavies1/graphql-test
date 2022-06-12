package com.amido.graphqltest.graphql.mutation;

import com.amido.graphqltest.domain.MarketplaceListing;
import com.amido.graphqltest.domain.MarketplaceReceipt;
import com.amido.graphqltest.service.MarketplaceListingService;
import com.amido.graphqltest.service.MarketplacePurchaseService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MarketplaceListingMutation implements GraphQLMutationResolver {

    private final MarketplaceListingService marketplaceListingService;
    private final MarketplacePurchaseService marketplacePurchaseService;

    public MarketplaceListing addMarketplaceListing(
            final Integer userId,
            final Integer itemId,
            final Integer requestedPrice) {
        return marketplaceListingService.addMarketplaceListing(userId, itemId, requestedPrice);
    }

    public MarketplaceReceipt buyItem(
            final String listingId,
            final Integer buyerId) {
        return marketplacePurchaseService.buyItem(listingId, buyerId);
    }

}
