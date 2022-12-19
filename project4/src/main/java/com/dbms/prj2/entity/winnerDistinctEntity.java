package com.dbms.prj2.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "departments")
@IdClass(winnerDistinctEntity.class)
public class winnerDistinctEntity implements Serializable{
    @Id
    private String driver_id;
//    @Id
    private String constructor_id;
    private String Name;

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getConstructor_id() {
        return constructor_id;
    }

    public void setConstructor_id(String constructor_id) {
        this.constructor_id = constructor_id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "winnerDistinctEntity{" +
                "driver_id='" + driver_id + '\'' +
                ", constructor_id='" + constructor_id + '\'' +
                ", Name='" + Name + '\'' +
                '}';
    }
}
