package com.dbms.prj2.repository;

import com.dbms.prj2.entity.Query3Entity;
import com.dbms.prj2.entity.SalaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Query3Repository extends JpaRepository<Query3Entity, String> {

    @Query(value = "SELECT R.dept_no, R.decade, COUNT(R.emp_no) AS Count_of_Emp, AVG(R.salary) AS Average_Salary FROM (SELECT EDS.emp_no,EDS.decade, EDS.salary,DE.dept_no FROM (SELECT EMPED.emp_no,EMPED.decade,S.salary FROM (SELECT emp_no,(YEAR(birth_date) DIV 10)*10 AS decade FROM employees) EMPED JOIN salaries S ON EMPED.emp_no = S.emp_no) EDS JOIN dept_emp DE ON EDS.emp_no=DE.emp_no) R GROUP BY R.dept_no,R.decade ORDER BY R.dept_no;", nativeQuery = true)
    public List<Query3Entity> getQuery3();
}
