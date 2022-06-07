insert into players (id, username, level)
VALUES (1, 'whiterose', 42),
       (2, 'hunter2', 13),
       (3, 'coolguy42', 50);

insert into items (id, name, effect)
VALUES (1, 'Red Potion of Healing', 'Restore 50HP'),
       (2, 'Blue Potion of Mana', 'Restore 20MP'),
       (3, 'Green Potion of Magic', 'Restore 50HGP');

insert into player_items (player_id, item_id)
values (2, 1),
       (2, 3),
       (3, 2);