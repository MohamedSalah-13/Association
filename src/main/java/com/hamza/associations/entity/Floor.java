package com.hamza.associations.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "floor_number")
public class Floor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int number_floor;
    private double amount;


//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "association_id")
//    private Association association;

    public Floor() {
    }

    public Floor(int number_floor, double amount) {
        this.number_floor = number_floor;
        this.amount = amount;
    }

}

