package com.hamza.associations.view.details;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Details {
    private StringProperty name = new SimpleStringProperty();
    private DoubleProperty jan = new SimpleDoubleProperty();
    private DoubleProperty feb = new SimpleDoubleProperty();
    private DoubleProperty mar = new SimpleDoubleProperty();
    private DoubleProperty april = new SimpleDoubleProperty();
    private DoubleProperty may = new SimpleDoubleProperty();
    private DoubleProperty jun = new SimpleDoubleProperty();
    private DoubleProperty july = new SimpleDoubleProperty();
    private DoubleProperty aug = new SimpleDoubleProperty();
    private DoubleProperty sep = new SimpleDoubleProperty();
    private DoubleProperty oct = new SimpleDoubleProperty();
    private DoubleProperty nov = new SimpleDoubleProperty();
    private DoubleProperty des = new SimpleDoubleProperty();
    private DoubleProperty totals = new SimpleDoubleProperty();
    private int year;

    public Details(String name, double jan, double feb, double mar, double april, double may,
                   double jun, double july, double aug, double sep, double oct, double nov, double des, double totals) {
        this.name = new SimpleStringProperty(name);
        this.jan = new SimpleDoubleProperty(jan);
        this.feb = new SimpleDoubleProperty(feb);
        this.mar = new SimpleDoubleProperty(mar);
        this.april = new SimpleDoubleProperty(april);
        this.may = new SimpleDoubleProperty(may);
        this.jun = new SimpleDoubleProperty(jun);
        this.july = new SimpleDoubleProperty(july);
        this.aug = new SimpleDoubleProperty(aug);
        this.sep = new SimpleDoubleProperty(sep);
        this.oct = new SimpleDoubleProperty(oct);
        this.nov = new SimpleDoubleProperty(nov);
        this.des = new SimpleDoubleProperty(des);
        this.totals = new SimpleDoubleProperty(totals);
    }
    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getJan() {
        return jan.get();
    }

    public DoubleProperty janProperty() {
        return jan;
    }

    public void setJan(double jan) {
        this.jan.set(jan);
    }

    public double getFeb() {
        return feb.get();
    }

    public DoubleProperty febProperty() {
        return feb;
    }

    public void setFeb(double feb) {
        this.feb.set(feb);
    }

    public double getMar() {
        return mar.get();
    }

    public DoubleProperty marProperty() {
        return mar;
    }

    public void setMar(double mar) {
        this.mar.set(mar);
    }

    public double getApril() {
        return april.get();
    }

    public DoubleProperty aprilProperty() {
        return april;
    }

    public void setApril(double april) {
        this.april.set(april);
    }

    public double getMay() {
        return may.get();
    }

    public DoubleProperty mayProperty() {
        return may;
    }

    public void setMay(double may) {
        this.may.set(may);
    }

    public double getJun() {
        return jun.get();
    }

    public DoubleProperty junProperty() {
        return jun;
    }

    public void setJun(double jun) {
        this.jun.set(jun);
    }

    public double getJuly() {
        return july.get();
    }

    public DoubleProperty julyProperty() {
        return july;
    }

    public void setJuly(double july) {
        this.july.set(july);
    }

    public double getAug() {
        return aug.get();
    }

    public DoubleProperty augProperty() {
        return aug;
    }

    public void setAug(double aug) {
        this.aug.set(aug);
    }

    public double getSep() {
        return sep.get();
    }

    public DoubleProperty sepProperty() {
        return sep;
    }

    public void setSep(double sep) {
        this.sep.set(sep);
    }

    public double getOct() {
        return oct.get();
    }

    public DoubleProperty octProperty() {
        return oct;
    }

    public void setOct(double oct) {
        this.oct.set(oct);
    }

    public double getNov() {
        return nov.get();
    }

    public DoubleProperty novProperty() {
        return nov;
    }

    public void setNov(double nov) {
        this.nov.set(nov);
    }

    public double getDes() {
        return des.get();
    }

    public DoubleProperty desProperty() {
        return des;
    }

    public void setDes(double des) {
        this.des.set(des);
    }

    public double getTotals() {
        return totals.get();
    }

    public DoubleProperty totalsProperty() {
        return totals;
    }

    public void setTotals(double totals) {
        this.totals.set(totals);
    }
}