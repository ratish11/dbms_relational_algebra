package com.dbms.prj2.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "departments")
@IdClass(Query3Entity.class)
public class Query3Entity implements Serializable{
    @Id
    private String dept_no;
    @Id
    private Integer decade;

    private Integer Count_of_Emp;

    private Float Average_Salary;

    public String getDept_no() {
        return dept_no;
    }

    public Integer getDecade() {
        return decade;
    }

    public Integer getCount_of_Emp() {
        return Count_of_Emp;
    }

    public Float getAverage_Salary() {
        return Average_Salary;
    }

    @Override
    public String toString() {
        return "Query3Entity{" +
                "dept_no='" + dept_no + '\'' +
                ", decade=" + decade +
                ", Count_of_Emp=" + Count_of_Emp +
                ", Average_Salary=" + Average_Salary +
                '}';
    }
}
