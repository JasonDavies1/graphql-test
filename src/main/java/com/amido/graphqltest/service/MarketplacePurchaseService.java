package com.amido.graphqltest.service;

import com.amido.graphqltest.domain.MarketplaceReceipt;

public interface MarketplacePurchaseService {

    MarketplaceReceipt buyItem(String listingId,
                               int playerId);

}
