package com.amido.graphqltest.config;


import com.amido.graphqltest.graphql.mutation.PlayerMutation;
import com.amido.graphqltest.graphql.query.PlayerQuery;
import com.amido.graphqltest.graphql.resolver.ItemResolver;
import com.amido.graphqltest.graphql.resolver.PlayerResolver;
import com.amido.graphqltest.service.ItemService;
import com.amido.graphqltest.service.ItemServiceImpl;
import com.amido.graphqltest.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GraphQlConfiguration {

    private final PlayerService playerService;

    @Bean
    public ItemService itemRepository() {
        return new ItemServiceImpl(playerService);
    }

    @Bean
    public PlayerMutation playerMutation() {
        return new PlayerMutation(playerService);
    }

    @Bean
    public PlayerQuery playerQuery() {
        return new PlayerQuery(playerService, itemRepository());
    }

    @Bean
    public ItemResolver itemResolver() {
        return new ItemResolver();
    }

    @Bean
    public PlayerResolver playerResolver() {
        return new PlayerResolver();
    }

}
