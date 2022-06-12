package com.amido.graphqltest.domain.dto;

import com.amido.graphqltest.domain.Item;
import lombok.Data;

@Data
public class ItemDto {

    private final int id;
    private final String name;
    private final String effect;

    public static ItemDto from(final Item item) {
        return new ItemDto(item.getId(),
                item.getName(),
                item.getEffect());
    }

}
