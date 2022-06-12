package com.amido.graphqltest.graphql.query;

import com.amido.graphqltest.domain.MarketplaceListing;
import com.amido.graphqltest.domain.MarketplaceReceipt;
import com.amido.graphqltest.repository.MarketplaceListingRepository;
import com.amido.graphqltest.repository.MarketplaceReceiptRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class MarketplaceQuery implements GraphQLQueryResolver {

    private final MarketplaceListingRepository marketplaceListingRepository;

    private final MarketplaceReceiptRepository marketplaceReceiptRepository;

    public List<MarketplaceListing> getAllMarketplaceListings() {
        return marketplaceListingRepository.findAll();
    }

    public List<MarketplaceReceipt> getAllMarketplaceReceipts() {
        final List<MarketplaceReceipt> receipts = new ArrayList<>();
        marketplaceReceiptRepository.findAll().forEach(receipts::add);
        return receipts;
    }

}
