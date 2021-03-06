package com.amido.graphqltest.graphql.mutation;

import com.amido.graphqltest.domain.Player;
import com.amido.graphqltest.service.PlayerService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayerMutation implements GraphQLMutationResolver {

    private final PlayerService playerService;

    public Player addNewPlayer(final String username) {
        return playerService.addNewPlayer(username);
    }

    public Integer removePlayer(final String username) {
        return playerService.removePlayerByUsername(username);
    }

}
