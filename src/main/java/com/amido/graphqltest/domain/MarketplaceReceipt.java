package com.amido.graphqltest.domain;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class MarketplaceReceipt {
    private UUID id;
    private Player seller;
    private Player buyer;
    private MarketplaceListing listing;
    private Date transactionDate;
}
