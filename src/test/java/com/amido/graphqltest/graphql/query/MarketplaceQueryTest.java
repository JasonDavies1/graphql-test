package com.amido.graphqltest.graphql.query;

import com.amido.graphqltest.domain.MarketplaceListing;
import com.amido.graphqltest.repository.MarketplaceListingRepository;
import com.amido.graphqltest.repository.MarketplaceReceiptRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

public class MarketplaceQueryTest {

    private final MarketplaceListingRepository marketplaceListingRepository = mock(MarketplaceListingRepository.class);
    private final MarketplaceReceiptRepository marketplaceReceiptRepository = mock(MarketplaceReceiptRepository.class);
    private final MarketplaceQuery marketplaceQuery = new MarketplaceQuery(
            marketplaceListingRepository,
            marketplaceReceiptRepository);

    @Test
    public void givenAllMarketListingsAreRequested_WhenSearchingForMarketListings_ThenThisActionIsPerformedUsingTheMarketListingRepository() {
        final List<MarketplaceListing> allMarketplaceListings = marketplaceQuery.getAllMarketplaceListings();

        then(marketplaceListingRepository)
                .should()
                .findAll();
    }

}