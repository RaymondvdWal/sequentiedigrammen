package com.example.controllerles10.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "wallbracket")
public class WallBracket {

    @Id
    @GeneratedValue
    Long id;
    String size;
    Boolean adjustable;
    String name;
    Double price;

    @ManyToMany(mappedBy = "wallBrackets")
    private List<Television> televisions;
}
