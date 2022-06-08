package com.amido.graphqltest.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String effect;

    @ManyToMany(
            fetch = FetchType.EAGER,
            mappedBy = "inventory")
    private List<Player> playersWithItem;
}
