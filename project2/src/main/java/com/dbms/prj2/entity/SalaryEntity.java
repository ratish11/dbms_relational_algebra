package com.dbms.prj2.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salaries")
public class SalaryEntity {
    @Id
    private String dept_name;
    private Float ratio;

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public Float getRatio() {
        return ratio;
    }

    public void setRatio(Float ratio) {
        this.ratio = ratio;
    }

    @Override
    public String toString() {
        return "SalaryEntity{" +
                "dept_name='" + dept_name + '\'' +
                ", ratio=" + ratio +
                '}';
    }
}
