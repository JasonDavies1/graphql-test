package com.amido.graphqltest.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private int level;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "player_items",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> inventory = new ArrayList<>();

}
