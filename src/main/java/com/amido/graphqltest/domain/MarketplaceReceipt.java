package com.amido.graphqltest.domain;

import com.amido.graphqltest.domain.dto.ItemDto;
import com.amido.graphqltest.domain.dto.PlayerDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.Date;

@Getter
@Setter
@Document(indexName = "marketplace-receipts")
public class MarketplaceReceipt {
    @Id
    private String id;
    private PlayerDto seller;
    private PlayerDto buyer;
    private ItemDto item;
    private int sellPrice;
    private Date transactionDate;
}
