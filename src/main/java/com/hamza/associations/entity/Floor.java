package com.hamza.associations.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "floor_number")
public class Floor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int number_floor;
    private double amount;


    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "association_id")
    private Association association;

    public Floor() {
    }

    public Floor(int number_floor, double amount) {
        super();
        this.number_floor = number_floor;
        this.amount = amount;
    }

    public Floor(int number_floor, double amount, Association association) {
        super();
        this.number_floor = number_floor;
        this.amount = amount;
        this.association = association;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber_floor() {
        return number_floor;
    }

    public void setNumber_floor(int number_floor) {
        this.number_floor = number_floor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Association getAssociation() {
        return association;
    }

    public void setAssociation(Association association) {
        this.association = association;
    }

    @Override
    public String toString() {
        return "Floor{" +
                "id=" + id +
                ", number_floor=" + number_floor +
                ", amount=" + amount +
                ", association=" + association +
                '}';
    }
}

