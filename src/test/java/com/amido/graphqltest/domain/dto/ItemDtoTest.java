package com.amido.graphqltest.domain.dto;

import com.amido.graphqltest.domain.Item;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ItemDtoTest {

    @Test
    public void canConvertFromDomainItem() {
        final Item item = new Item();
        item.setId(1);
        item.setName("Potion");
        item.setEffect("Restore 10HP");

        assertThat(ItemDto.from(item))
                .satisfies(dto -> {
                    assertThat(dto.getId())
                            .isEqualTo(1);
                    assertThat(dto.getName())
                            .isEqualTo("Potion");
                    assertThat(dto.getEffect())
                            .isEqualTo("Restore 10HP");
                });
    }

}