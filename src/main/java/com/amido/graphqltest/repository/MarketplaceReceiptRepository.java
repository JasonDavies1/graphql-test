package com.amido.graphqltest.repository;

import com.amido.graphqltest.domain.MarketplaceReceipt;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MarketplaceReceiptRepository extends ElasticsearchRepository<MarketplaceReceipt, String> {
}
