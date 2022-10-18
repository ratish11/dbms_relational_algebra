package com.dbms.prj2.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "departments")
public class DoSEntity1 {
    @Id
    private String e1_emp;
    private String e1_dept, e1_from, e1_to, e2_emp, e2_dept, e2_from, e2_to;

    public String getE1_emp() {
        return e1_emp;
    }

    public void setE1_emp(String e1_emp) {
        this.e1_emp = e1_emp;
    }

    public String getE1_dept() {
        return e1_dept;
    }

    public void setE1_dept(String e1_dept) {
        this.e1_dept = e1_dept;
    }

    public String getE1_from() {
        return e1_from;
    }

    public void setE1_from(String e1_from) {
        this.e1_from = e1_from;
    }

    public String getE1_to() {
        return e1_to;
    }

    public void setE1_to(String e1_to) {
        this.e1_to = e1_to;
    }

    public String getE2_emp() {
        return e2_emp;
    }

    public void setE2_emp(String e2_emp) {
        this.e2_emp = e2_emp;
    }

    public String getE2_dept() {
        return e2_dept;
    }

    public void setE2_dept(String e2_dept) {
        this.e2_dept = e2_dept;
    }

    public String getE2_from() {
        return e2_from;
    }

    public void setE2_from(String e2_from) {
        this.e2_from = e2_from;
    }

    public String getE2_to() {
        return e2_to;
    }

    public void setE2_to(String e2_to) {
        this.e2_to = e2_to;
    }

    @Override
    public String toString() {
        return "DoSEntity1{" +
                "e1_emp='" + e1_emp + '\'' +
                ", e1_dept='" + e1_dept + '\'' +
                ", e1_from='" + e1_from + '\'' +
                ", e1_to='" + e1_to + '\'' +
                ", e2_emp='" + e2_emp + '\'' +
                ", e2_dept='" + e2_dept + '\'' +
                ", e2_from='" + e2_from + '\'' +
                ", e2_to='" + e2_to + '\'' +
                '}';
    }
}
