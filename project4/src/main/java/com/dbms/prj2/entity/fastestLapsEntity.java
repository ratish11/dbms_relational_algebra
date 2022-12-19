package com.dbms.prj2.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "departments")
public class fastestLapsEntity{
    @Id
    private Float fastest_lap_speed;
    private Integer race_id, driver_id;
    private String Name, championship;

    public Float getFastest_lap_speed() {
        return fastest_lap_speed;
    }

    public void setFastest_lap_speed(Float fastest_lap_speed) {
        this.fastest_lap_speed = fastest_lap_speed;
    }

    public Integer getRace_id() {
        return race_id;
    }

    public void setRace_id(Integer race_id) {
        this.race_id = race_id;
    }

    public Integer getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(Integer driver_id) {
        this.driver_id = driver_id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getChampionship() {
        return championship;
    }

    public void setChampionship(String championship) {
        this.championship = championship;
    }

    @Override
    public String toString() {
        return "fastestLapsEntity{" +
                "fastest_lap_speed=" + fastest_lap_speed +
                ", race_id=" + race_id +
                ", driver_id=" + driver_id +
                ", Name='" + Name + '\'' +
                ", championship='" + championship + '\'' +
                '}';
    }
}
