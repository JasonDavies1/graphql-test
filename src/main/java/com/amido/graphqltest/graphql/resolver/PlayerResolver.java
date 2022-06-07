package com.amido.graphqltest.graphql.resolver;

import com.amido.graphqltest.domain.Player;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

@Component
public class PlayerResolver implements GraphQLResolver<Player> {
}
