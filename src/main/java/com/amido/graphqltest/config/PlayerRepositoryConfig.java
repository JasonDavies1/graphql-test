package com.amido.graphqltest.config;

import com.amido.graphqltest.domain.Item;
import com.amido.graphqltest.domain.Player;
import com.amido.graphqltest.service.PlayerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class PlayerRepositoryConfig {

    @Bean
    public PlayerServiceImpl playerRepository() {
        return new PlayerServiceImpl(
                Stream.of(
                        player(1, "whiterose", 42),
                        player(2, "hunter2", 13, redPotion(), greenPotion()),
                        player(3, "coolguy42", 50, bluePotion())
                ).collect(Collectors.toList())
        );
    }

    private Player player(
            final Integer id,
            final String username,
            final int level,
            final Item... items){
        final Player player = new Player();
        player.setId(id);
        player.setUsername(username);
        player.setLevel(level);
        player.getInventory().addAll(Arrays.asList(items));
        return player;
    }

    private Item redPotion(){
        return item(1, "Red Potion of Healing", "Restore 50HP");
    }

    private Item bluePotion(){
        return item(2, "Blue Potion of Mana", "Restore 20MP");
    }

    private Item greenPotion(){
        return item(3, "Green Potion of Magic", "Restore 50MGP");
    }

    private Item item(
            final Integer id,
            final String name,
            final String effect) {
        final Item item = new Item();
        item.setId(id);
        item.setName(name);
        item.setEffect(effect);
        return item;
    }

}
