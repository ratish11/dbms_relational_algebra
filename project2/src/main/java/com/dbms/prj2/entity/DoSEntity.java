package com.dbms.prj2.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "departments")
public class DoSEntity {
    @Id
    private String emp1;
    private String emp2;

    public String getEmp1() {
        return emp1;
    }

    public void setEmp1(String emp1) {
        this.emp1 = emp1;
    }

    public String getEmp2() {
        return emp2;
    }

    public void setEmp2(String emp2) {
        this.emp2 = emp2;
    }

    @Override
    public String toString() {
        return "DoSEntity{" +
                "emp1='" + emp1 + '\'' +
                ", emp2='" + emp2 + '\'' +
                '}';
    }
}
