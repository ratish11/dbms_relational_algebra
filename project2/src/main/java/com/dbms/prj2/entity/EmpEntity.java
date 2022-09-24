package com.dbms.prj2.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "departments")
public class EmpEntity {
    @Id
//    @OneToMany
//    @JoinColumn(name = "emp_no")
    private String Name;
//    private String first_name, last_name,gender;
//    private java.sql.Date birth_date, hire_date;
//    private enum gender {M, F}

    public String getName() {
        return Name;
    }

//    public String getFirst_name() {
//        return first_name;
//    }
//
//    public String getLast_name() {
//        return last_name;
//    }
//
//    public String getGender() {
//        return gender;
//    }

//    public Date getBirth_date() {
//        return birth_date;
//    }
//
//    public Date getHire_date() {
//        return hire_date;
//    }

    @Override
    public String toString() {
        return "EmpEntity{" +
                "Name='" + Name + '\'' +
//                ", first_name='" + first_name + '\'' +
//                ", last_name='" + last_name + '\'' +
//                ", gender='" + gender + '\'' +
//                ", birth_date=" + birth_date +
//                ", hire_date=" + hire_date +
                '}';
    }
}
