package com.amido.graphqltest.repository;

import com.amido.graphqltest.domain.Item;

import java.util.List;

public interface ItemRepository {

    List<Item> getPlayerInventoryItems(String userId);

}
