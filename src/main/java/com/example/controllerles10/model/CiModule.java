package com.example.controllerles10.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "ci-module")
public class CiModule {

    @Id
    @GeneratedValue
    Long id;
    String name;
    String type;
    Double price;

    @ManyToOne
    @JoinColumn(name = "television_id")
    private Television television;

}
