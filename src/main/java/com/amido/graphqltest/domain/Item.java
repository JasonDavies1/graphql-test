package com.amido.graphqltest.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String effect;

    @ToString.Exclude
    @ManyToMany(
            fetch = FetchType.EAGER,
            mappedBy = "inventory")
    private List<Player> playersWithItem;
}
