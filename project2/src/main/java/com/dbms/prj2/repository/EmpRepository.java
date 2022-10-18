package com.dbms.prj2.repository;

import com.dbms.prj2.entity.EmpEntity;
import com.dbms.prj2.entity.SalaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpRepository extends JpaRepository<EmpEntity, String > {

    @Query(value="SELECT CONCAT(last_name, \" \", first_name) AS Name FROM employees WHERE emp_no IN (SELECT emp_no FROM dept_manager WHERE datediff(to_date, from_date) IN (SELECT MAX(datediff(to_date, from_date)) AS term FROM dept_manager));", nativeQuery = true)
    public List<EmpEntity> getQuery2();
    @Query(value="SELECT DISTINCT(FMGR.Name) FROM (SELECT FE.emp_no,FE.birth_date, CONCAT(FE.last_name, \" \", FE.first_name) AS Name, FE.gender FROM (SELECT * FROM employees WHERE gender='F' AND  birth_date<'1990-01-01') FE JOIN dept_manager DE ON FE.emp_no = DE.emp_no) FMGR JOIN salaries S ON FMGR.emp_no = S.emp_no WHERE S.salary>80000;", nativeQuery = true)
    public List<EmpEntity> getQuery4();
}

