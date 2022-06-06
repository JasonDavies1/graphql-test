package com.amido.graphqltest.graphql.mutation;

import com.amido.graphqltest.domain.Player;
import com.amido.graphqltest.repository.PlayerRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayerMutation implements GraphQLMutationResolver {

    private final PlayerRepository playerRepository;

    public Player addNewPlayer(final String username) {
        return playerRepository.addNewPlayer(username);
    }

}
