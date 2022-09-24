package com.dbms.prj2.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "departments")
public class EmpEntity {
    @Id

    private String Name;

    public String getName() {
        return Name;
    }

    @Override
    public String toString() {
        return "EmpEntity{" +
                "Name='" + Name + '\'' +
                '}';
    }
}
