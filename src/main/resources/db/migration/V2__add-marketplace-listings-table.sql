-- Create table for marketplace listings
CREATE TABLE marketplace_listings
(
    id              VARCHAR(36) PRIMARY KEY,
    seller_id       int          NOT NULL,
    item_id         int          NOT NULL,
    requested_price int          NOT NULL,
    status          varchar(255) NOT NULL,

    constraint fk_market_place_seller_id
        foreign key (seller_id)
            references players (id),
    constraint fk_market_place_item_id
        foreign key (item_id)
            references items (id)
);

-- Add seed data for marketplace listings
INSERT INTO marketplace_listings (id, seller_id, item_id, requested_price, status)
VALUES ('d3283bea-dc18-486f-aa8a-60455b566d47', 1, 2, 200, 'For Sale'),
       ('f0765e7a-d7a9-43fb-9ffc-39a97061e518', 1, 2, 100, 'For Sale');