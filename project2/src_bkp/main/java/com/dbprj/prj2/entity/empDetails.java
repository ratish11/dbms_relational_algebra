package com.dbprj.prj2.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.persistence.Table;

@Entity
@Table(name = "departments")
public class empDetails {
    @Id
    private String dept_no;
    private String dept_name;
//    private String emp2;
//    private String emp3;

    public String getEmp1() {
        return dept_no;
    }

    public String getEp2() {
        return dept_name;
    }
//
//    public String getEmp3() {
//        return emp3;
//    }

    public void setEmp1(String dept_no) {
        this.dept_no = dept_no;
    }

    public void setEmp2(String dept_name) {
        this.dept_name = dept_name;
    }

//    public void setEmp3(String emp3) {
//        this.emp3 = emp3;
//    }
    @Override
    public String toString() {
        return "empDetails{" +
                "dept_no='" + dept_no + '\'' +
                ", dept_name='" + dept_name + '\'' +
//                ", emp3='" + emp3 + '\'' +
                '}';
    }

}
