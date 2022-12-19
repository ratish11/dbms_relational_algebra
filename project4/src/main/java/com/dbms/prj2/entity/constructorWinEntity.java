package com.dbms.prj2.entity;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;

@Entity
@Table(name = "departments")
public class constructorWinEntity implements Serializable{
    @Id
    private Integer race_id;
//    @Id
    private Integer constructor_id;
    private Integer max_points;
    private String name;

    public Integer getRace_id() {
        return race_id;
    }

    public void setRace_id(Integer race_id) {
        this.race_id = race_id;
    }

    public Integer getConstructor_id() {
        return constructor_id;
    }

    public void setConstructor_id(Integer constructor_id) {
        this.constructor_id = constructor_id;
    }

    public Integer getMax_points() {
        return max_points;
    }

    public void setMax_ppoints(Integer max_points) {
        this.max_points = max_points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
