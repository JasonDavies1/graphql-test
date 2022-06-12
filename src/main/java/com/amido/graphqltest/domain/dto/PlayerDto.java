package com.amido.graphqltest.domain.dto;

import com.amido.graphqltest.domain.Player;
import lombok.Data;

@Data
public class PlayerDto {
    private final int id;
    private final String username;
    private final int level;

    public static PlayerDto from(final Player player) {
        return new PlayerDto(player.getId(),
                player.getUsername(),
                player.getLevel());
    }
}
