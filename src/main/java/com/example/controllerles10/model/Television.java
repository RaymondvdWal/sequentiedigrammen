package com.example.controllerles10.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "televisions")
@NoArgsConstructor
public class Television {

        @Id
        @GeneratedValue
        public Long id;
        public String brand;
        public String type;
        public double price;
        public int screenSize;

        @JsonIgnore
        @OneToOne
        private RemoteController remoteController;

        @OneToMany(mappedBy = "television")
        private List<CiModule> ciModule;

        @ManyToMany(cascade = CascadeType.ALL)
        @JoinTable(
                name = "wallbracket_television",
                joinColumns = @JoinColumn(name = "wallbracket_id"),
                inverseJoinColumns = @JoinColumn(name = "television_id")
        )
        private List<WallBracket> wallBrackets;

        public Television(String brand, double price, String type, int screenSize) {
                this.brand = brand;
                this.price = price;
                this.type = type;
                this.screenSize = screenSize;
        }
}
