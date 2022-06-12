-- Create tables
CREATE TABLE players
(
    id       integer PRIMARY KEY AUTO_INCREMENT,
    username varchar(255) NOT NULL,
    level    integer default 0
);

CREATE TABLE items
(
    id     integer PRIMARY KEY AUTO_INCREMENT,
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

-- Add seed data
insert into players (username, level)
VALUES ('whiterose', 42),
       ('hunter2', 13),
       ('coolguy42', 50);

insert into items (name, effect)
VALUES ('Red Potion of Healing', 'Restore 50HP'),
       ('Blue Potion of Mana', 'Restore 20MP'),
       ('Green Potion of Magic', 'Restore 50MGP');

insert into player_items (player_id, item_id)
values (2, 1),
       (2, 3),
       (3, 2);