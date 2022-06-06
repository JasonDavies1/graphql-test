package com.amido.graphqltest.graphql.mutation;

import com.amido.graphqltest.domain.Player;
import com.amido.graphqltest.repository.PlayerRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class PlayerMutationTest {

    private final PlayerRepository playerRepository = mock(PlayerRepository.class);
    private final PlayerMutation playerMutation = new PlayerMutation(playerRepository);

    @Test
    public void givenPlayerIsToBeAdded_WhenAddingPlayer_ThenThisActionIsPerformedUsingThePlayerRepository() {
        final Player result = playerMutation.addNewPlayer("coolguy42");

        then(playerRepository)
                .should()
                .addNewPlayer("coolguy42");
    }

}