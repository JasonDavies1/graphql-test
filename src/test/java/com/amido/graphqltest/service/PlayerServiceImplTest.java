package com.amido.graphqltest.service;

import com.amido.graphqltest.domain.Item;
import com.amido.graphqltest.domain.Player;
import com.amido.graphqltest.exception.PlayerNotFoundException;
import com.amido.graphqltest.repository.PlayerRepository;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class PlayerServiceImplTest {

    private final PlayerRepository playerRepository = mock(PlayerRepository.class);
    private PlayerServiceImpl playerService = new PlayerServiceImpl(playerRepository);

    @Test
    public void givenRepositoryContainsOnePlayer_WhenSearchingForAllPlayers_ThenASingletonListIsReturned() {
        given(playerRepository.findAll())
                .willReturn(Collections.singletonList(
                        testPlayer()
                ));

        final List<Player> result = playerService.findAllPlayers();

        assertThat(result)
                .singleElement()
                .satisfies(p -> {
                    assertThat(p.getUsername())
                            .isEqualTo("coolguy42");
                });
    }

    @Test
    public void givenPlayerIsAdded_WhenNoPlayerHasRequestedUsername_ThenNewPlayerIsAddedWithEmptyInventory() {
        final Player persistedPlayer = new Player();
        persistedPlayer.setId(2);
        persistedPlayer.setUsername("alice");
        given(playerRepository.save(any()))
                .willReturn(persistedPlayer);

        final Player result = playerService.addNewPlayer("alice");

        assertThat(result.getId())
                .isEqualTo(2);
        assertThat(result.getInventory())
                .isEmpty();
    }

    @Test
    public void givenPlayerISAdded_WhenPlayerAlreadyHasUsername_ThenExistingPlayerIsReturnedWithExistingInventory() {
        given(playerRepository.findByUsername("coolguy42"))
                .willReturn(Optional.of(testPlayer()));

        final Player result = playerService.addNewPlayer("coolguy42");

        assertThat(result.getId())
                .isEqualTo(1);
        assertThat(result.getInventory())
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.getName())
                            .isEqualTo("Red Potion");
                });
    }

    @Test
    public void givenPlayerIsSearchedForById_WhenPlayerExistsWithId_ThenPlayerIsReturned() {
        given(playerRepository.findById(1))
                .willReturn(Optional.of(testPlayer()));

        final Player result = playerService.findPlayerById(1);

        assertThat(result.getUsername())
                .isEqualTo("coolguy42");
    }

    @Test
    public void givenPlayerIsSearchedForById_WhenPlayerDoesNotExistWithId_ThenExceptionIsThrown() {
        given(playerRepository.findById(999999999))
                .willReturn(Optional.empty());

        assertThatCode(() -> playerService.findPlayerById(999999999))
                .isInstanceOf(PlayerNotFoundException.class)
                .hasMessage("Player with id 999999999 not found");
    }

    private Player testPlayer() {
        final Player player = new Player();
        player.setId(1);
        player.setLevel(12);
        player.setUsername("coolguy42");
        player.setInventory(Collections.singletonList(testItem()));
        return player;
    }

    private Item testItem() {
        final Item item = new Item();
        item.setName("Red Potion");
        return item;
    }


}