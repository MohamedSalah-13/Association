package com.hamza.associations.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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


    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "association_id")
    private Association association;

    public Floor() {
    }

    public Floor(int number_floor, double amount) {
        super();
        this.number_floor = number_floor;
        this.amount = amount;
    }

    public Floor(int number_floor, double amount,Association association) {
        super();
        this.number_floor = number_floor;
        this.amount = amount;
        this.association=association;
    }

}

