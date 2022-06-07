package com.amido.graphqltest.graphql.mutation;

import com.amido.graphqltest.domain.Player;
import com.amido.graphqltest.service.PlayerService;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class PlayerMutationTest {

    private final PlayerService playerService = mock(PlayerService.class);
    private final PlayerMutation playerMutation = new PlayerMutation(playerService);

    @Test
    public void givenPlayerIsToBeAdded_WhenAddingPlayer_ThenThisActionIsPerformedUsingThePlayerRepository() {
        final Player result = playerMutation.addNewPlayer("coolguy42");

        then(playerService)
                .should()
                .addNewPlayer("coolguy42");
    }

}