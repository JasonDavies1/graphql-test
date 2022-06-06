package com.amido.graphqltest.config;


import com.amido.graphqltest.graphql.mutation.PlayerMutation;
import com.amido.graphqltest.graphql.query.PlayerQuery;
import com.amido.graphqltest.graphql.resolver.ItemResolver;
import com.amido.graphqltest.graphql.resolver.PlayerResolver;
import com.amido.graphqltest.repository.ItemRepository;
import com.amido.graphqltest.repository.ItemRepositoryImpl;
import com.amido.graphqltest.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GraphQlConfiguration {

    private final PlayerRepository playerRepository;

    @Bean
    public ItemRepository itemRepository() {
        return new ItemRepositoryImpl(playerRepository);
    }

    @Bean
    public PlayerMutation playerMutation() {
        return new PlayerMutation(playerRepository);
    }

    @Bean
    public PlayerQuery playerQuery() {
        return new PlayerQuery(playerRepository, itemRepository());
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
