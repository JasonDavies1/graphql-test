package com.amido.graphqltest.graphql.mutation;

import com.amido.graphqltest.domain.MarketplaceListing;
import com.amido.graphqltest.service.MarketplaceListingService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MarketplaceListingMutation implements GraphQLMutationResolver {

    private final MarketplaceListingService marketplaceListingService;

    public MarketplaceListing addMarketplaceListing(
            final Integer userId,
            final Integer itemId,
            final Integer requestedPrice) {
        return marketplaceListingService.addMarketplaceListing(userId, itemId, requestedPrice);
    }

}
