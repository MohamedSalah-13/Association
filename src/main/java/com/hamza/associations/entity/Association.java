package com.hamza.associations.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
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
    private LocalDate date_end;


//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "association_id", referencedColumnName = "id")
//    private List<Floor> floor = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "association")
    private List<Floor> floor;

    public Association() {
    }

    public Association(String name, double amount, Date start_date, int count_month, String notes) {
        super();
        this.name = name;
        this.amount = amount;
        this.start_date = start_date;
        this.count_month = count_month;
        this.notes = notes;

    }
}
