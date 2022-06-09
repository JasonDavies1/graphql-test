package com.amido.graphqltest.graphql.query;

import com.amido.graphqltest.domain.MarketplaceListing;
import com.amido.graphqltest.repository.MarketplaceListingRepository;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.error.Mark;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

public class MarketplaceQueryTest {

    private final MarketplaceListingRepository marketplaceListingRepository = mock(MarketplaceListingRepository.class);
    private final MarketplaceQuery marketplaceQuery = new MarketplaceQuery(marketplaceListingRepository);

    @Test
    public void givenAllMarketListingsAreRequested_WhenSearchingForMarketListings_ThenThisActionIsPerformedUsingTheMarketListingRepository(){
        final List<MarketplaceListing> allMarketplaceListings = marketplaceQuery.getAllMarketplaceListings();

        then(marketplaceListingRepository)
                .should()
                .findAll();
    }

}