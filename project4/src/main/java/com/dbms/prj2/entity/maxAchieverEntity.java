package com.dbms.prj2.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="databases")
public class maxAchieverEntity {
    @Id
    private Integer year;
    private Integer constructorId, driverId, Max_Points;
    private String Name;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getConstructorId() {
        return constructorId;
    }

    public void setConstructorId(Integer constructorId) {
        this.constructorId = constructorId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getMax_Points() {
        return Max_Points;
    }

    public void setMax_Points(Integer max_Points) {
        Max_Points = max_Points;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "maxAchiever{" +
                ", Name='" + Name + '\'' +
                "year=" + year +
                ", constructorId=" + constructorId +
                ", driverId=" + driverId +
                ", Max_Points=" + Max_Points +
                '}';
    }
}
