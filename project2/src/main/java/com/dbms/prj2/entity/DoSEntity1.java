package com.dbms.prj2.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "departments")
public class DoSEntity1 {
    @Id
    private Integer e1;
    private Integer e2;

    public Integer getEmp1() {
        return e1;
    }

    public void setEmp1(String emp1) {
        this.e1 = e1;
    }

    public Integer getEmp2() {
        return e2;
    }

    public void setEmp2(String emp2) {
        this.e2 = e2;
    }

    @Override
    public String toString() {
        return "DoSEntity{" +
                "emp1='" + e1 + '\'' +
                ", emp2='" + e2 + '\'' +
                '}';
    }
}
