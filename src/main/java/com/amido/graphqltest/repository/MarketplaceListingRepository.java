package com.amido.graphqltest.repository;

import com.amido.graphqltest.domain.MarketplaceListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketplaceListingRepository extends JpaRepository<MarketplaceListing, String> {
}
