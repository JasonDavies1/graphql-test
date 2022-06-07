CREATE TABLE players
(
    id       integer PRIMARY KEY UNIQUE NOT NULL,
    username varchar(255)               NOT NULL,
    level    integer default 0
);

CREATE TABLE items
(
    id     integer PRIMARY KEY,
    name   varchar(255) NOT NULL,
    effect varchar(255) NOT NULL
);

CREATE TABLE player_items
(
    player_id integer,
    item_id   integer,
    constraint fk_player_id
        foreign key (player_id)
            references players (id),
    constraint fk_item_id
        foreign key (item_id)
            references items (id),
    constraint pk_player_items
        primary key (player_id, item_id)
);