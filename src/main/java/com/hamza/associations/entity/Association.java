package com.hamza.associations.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name = "association")
public class Association {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double amount;
    @Temporal(TemporalType.DATE)
    private Date start_date;
    private int count_month;
    @Column(columnDefinition = "varchar(255) default 'No Data'")
    private String notes;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "create_date", nullable = false)
    @Column(name = "create_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date create_date = new Date();

    @Transient
    private Date date_end;

   /* @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date", nullable = false)
    private Date modifyDate = new Date();*/


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "association_id", referencedColumnName = "id")
    private List<Floor> floor = new ArrayList<>();

    public Association() {
    }

    public Association(String name, double amount, Date start_date, int count_month, String notes) {
        this.name = name;
        this.amount = amount;
        this.start_date = start_date;
        this.count_month = count_month;
        this.notes = notes;

    }

    public Association(String name, double amount, Date start_date, int count_month, String notes, List<Floor> floorList) {
        this.name = name;
        this.amount = amount;
        this.start_date = start_date;
        this.count_month = count_month;
        this.notes = notes;
        this.floor = floorList;
    }

    public Association(Long associationId, String name, double amount, Date date, int countMonth, String notes, List<Floor> floorList) {
        this.id = associationId;
        this.name = name;
        this.amount = amount;
        this.start_date = date;
        this.count_month = countMonth;
        this.notes = notes;
        this.floor = floorList;
    }
}
