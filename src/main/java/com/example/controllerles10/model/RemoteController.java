package com.example.controllerles10.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "remote-controllers")
public class RemoteController {


    @Id
    @GeneratedValue
    Long id;
    String name;
    String compatibleWith;
    String batteryType;
    String brand;
    Double price;
    Integer originalStock;

    @OneToOne(mappedBy =  "remoteController")
    private Television television;
}
