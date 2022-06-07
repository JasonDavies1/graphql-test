package com.amido.graphqltest.service;

import com.amido.graphqltest.domain.Item;

import java.util.List;

public interface ItemService {

    List<Item> getPlayerInventoryItems(Integer userId);

}
