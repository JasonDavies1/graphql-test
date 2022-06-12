package com.amido.graphqltest.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "marketplace_listings")
public class MarketplaceListing {

    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;

    @OneToOne
    @JoinColumn(
            name = "seller_id",
            referencedColumnName = "id"
    )
    private Player seller;

    @OneToOne
    @JoinColumn(
            name = "item_id",
            referencedColumnName = "id"
    )
    private Item item;

    @Column(name = "requested_price")
    private int requestedPrice;

    private String status;
}
