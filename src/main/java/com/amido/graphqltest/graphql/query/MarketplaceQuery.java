package com.amido.graphqltest.graphql.query;

import com.amido.graphqltest.domain.MarketplaceListing;
import com.amido.graphqltest.repository.MarketplaceListingRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MarketplaceQuery implements GraphQLQueryResolver {

    private final MarketplaceListingRepository marketplaceListingRepository;

    public List<MarketplaceListing> getAllMarketplaceListings() {
        return marketplaceListingRepository.findAll();
    }

}
