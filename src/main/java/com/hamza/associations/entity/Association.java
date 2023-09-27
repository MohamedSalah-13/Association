package com.hamza.associations.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @OneToMany(mappedBy = "association", cascade = CascadeType.ALL)
    private List<Floor> floor = new ArrayList<>();

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public int getCount_month() {
        return count_month;
    }

    public void setCount_month(int count_month) {
        this.count_month = count_month;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public LocalDate getDate_end() {
        return date_end;
    }

    public void setDate_end(LocalDate date_end) {
        this.date_end = date_end;
    }

    public List<Floor> getFloor() {
        return floor;
    }

    public void setFloor(List<Floor> floor) {
        this.floor = floor;
    }

}
