package com.amido.graphqltest.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Player {
    private String id;
    private String username;
    private int level;
    private List<Item> inventory = new ArrayList<>();
}
