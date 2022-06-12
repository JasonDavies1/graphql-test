package com.amido.graphqltest.domain.dto;

import com.amido.graphqltest.domain.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlayerDtoTest {

    @Test
    public void canConvertFromDomainPlayer() {
        final Player player = new Player();
        player.setId(1);
        player.setUsername("player");
        player.setLevel(10);

        assertThat(PlayerDto.from(player))
                .satisfies(dto -> {
                    assertThat(dto.getId())
                            .isEqualTo(1);
                    assertThat(dto.getUsername())
                            .isEqualTo("player");
                    assertThat(dto.getLevel())
                            .isEqualTo(10);
                });
    }

}