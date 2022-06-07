package com.amido.graphqltest.repository;

import com.amido.graphqltest.domain.Item;
import com.amido.graphqltest.domain.Player;
import com.amido.graphqltest.exception.PlayerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class PlayerRepositoryImplTest {

    private PlayerRepositoryImpl playerRepository;

    @BeforeEach
    public void setUp() {
        playerRepository = new PlayerRepositoryImpl(
                Stream.of(testPlayer()).collect(Collectors.toList())
        );
    }

    @Test
    public void givenRepositoryContainsOnePlayer_WhenSearchingForAllPlayers_ThenASingletonListIsReturned() {
        final List<Player> result = playerRepository.findAllPlayers();

        assertThat(result)
                .singleElement()
                .satisfies(p -> {
                    assertThat(p.getUsername())
                            .isEqualTo("coolguy42");
                });
    }

    @Test
    public void givenPlayerIsAdded_WhenNoPlayerHasRequestedUsername_ThenNewPlayerIsAddedWithEmptyInventory() {
        final Player result = playerRepository.addNewPlayer("alice");

        assertThat(result.getInventory())
                .isEmpty();
    }

    @Test
    public void givenPlayerISAdded_WhenPlayerAlreadyHasUsername_ThenExistingPlayerIsReturnedWithExistingInventory() {
        final Player result = playerRepository.addNewPlayer("coolguy42");

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
        final Player result = playerRepository.findPlayerById(1);

        assertThat(result.getUsername())
                .isEqualTo("coolguy42");
    }

    @Test
    public void givenPlayerIsSearchedForById_WhenPlayerDoesNotExistWithId_ThenExceptionIsThrown() {
        assertThatCode(() -> playerRepository.findPlayerById(999999999))
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