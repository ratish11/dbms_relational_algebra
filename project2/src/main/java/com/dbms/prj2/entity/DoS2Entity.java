package com.dbms.prj2.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="databases")
public class DoS2Entity {
    @Id
    private Integer e1_emp;
    private Integer e3_emp, e2_emp;
    private String e1_dept, e3_dept, e2_dept;
    private Date e1_from, e1_to, e3_from, e3_to, e2_from, e2_to;

    public Integer getE1_emp() {
        return e1_emp;
    }

    public void setE1_emp(Integer e1_emp) {
        this.e1_emp = e1_emp;
    }

    public Integer getE3_emp() {
        return e3_emp;
    }

    public void setE3_emp(Integer e3_emp) {
        this.e3_emp = e3_emp;
    }

    public Integer getE2_emp() {
        return e2_emp;
    }

    public void setE2_emp(Integer e2_emp) {
        this.e2_emp = e2_emp;
    }

    public String getE1_dept() {
        return e1_dept;
    }

    public void setE1_dept(String e1_dept) {
        this.e1_dept = e1_dept;
    }

    public String getE3_dept() {
        return e3_dept;
    }

    public void setE3_dept(String e3_dept) {
        this.e3_dept = e3_dept;
    }

    public String getE2_dept() {
        return e2_dept;
    }

    public void setE2_dept(String e2_dept) {
        this.e2_dept = e2_dept;
    }

    public Date getE1_from() {
        return e1_from;
    }

    public void setE1_from(Date e1_from) {
        this.e1_from = e1_from;
    }

    public Date getE1_to() {
        return e1_to;
    }

    public void setE1_to(Date e1_to) {
        this.e1_to = e1_to;
    }

    public Date getE3_from() {
        return e3_from;
    }

    public void setE3_from(Date e3_from) {
        this.e3_from = e3_from;
    }

    public Date getE3_to() {
        return e3_to;
    }

    public void setE3_to(Date e3_to) {
        this.e3_to = e3_to;
    }

    public Date getE2_from() {
        return e2_from;
    }

    public void setE2_from(Date e2_from) {
        this.e2_from = e2_from;
    }

    public Date getE2_to() {
        return e2_to;
    }

    public void setE2_to(Date e2_to) {
        this.e2_to = e2_to;
    }

    @Override
    public String toString() {
        return "DoS2Entity{" +
                "e1_emp=" + e1_emp +
                ", e3_emp=" + e3_emp +
                ", e2_emp=" + e2_emp +
                ", e1_dept='" + e1_dept + '\'' +
                ", e3_dept='" + e3_dept + '\'' +
                ", e2_dept='" + e2_dept + '\'' +
                ", e1_from=" + e1_from +
                ", e1_to=" + e1_to +
                ", e3_from=" + e3_from +
                ", e3_to=" + e3_to +
                ", e2_from=" + e2_from +
                ", e2_to=" + e2_to +
                '}';
    }
}
