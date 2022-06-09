package com.amido.graphqltest.service;

import com.amido.graphqltest.domain.MarketplaceListing;

public interface MarketplaceListingService {

    MarketplaceListing addMarketplaceListing(int playerId,
                                             int itemId,
                                             int price);

}
