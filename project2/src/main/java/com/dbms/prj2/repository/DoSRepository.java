package com.dbms.prj2.repository;

import com.dbms.prj2.entity.DoSEntity1;
import com.dbms.prj2.entity.Query3Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoSRepository extends JpaRepository<DoSEntity1, String> {

    @Query(value = "SELECT D1.emp_no AS e1_emp, D1.dept_no AS e1_dept, D1.from_date AS e1_from, D1.to_date AS e1_to, D2.emp_no AS e2_emp, D2.dept_no AS e2_dept, D2.from_date AS e2_from, D2.to_date AS e2_to  FROM dept_emp D1 JOIN dept_emp D2 ON D1.dept_no=D2.dept_no AND (((D1.from_date<=D2.from_date) AND ((D1.to_date>=D2.from_date) OR (D1.to_date <= D2.to_date))) OR ((D2.from_date<=D1.from_date) AND ((D2.to_date>=D1.from_date) OR (D2.to_date <= D1.to_date))) OR ((D1.from_date >= D2.from_date) AND (D1.to_date <= D2.to_date)) OR ((D2.from_date >= D1.from_date) AND (D2.to_date <= D1.to_date))) WHERE NOT D1.emp_no = D2.emp_no AND D1.emp_no = :emp1 AND D2.emp_no= :emp2 ;", nativeQuery = true)
//@Query(value = "select emp_no as emp1, emp_no as emp2 from employees where emp_no = :emp1 or emp_no = :emp2 ;", nativeQuery = true)
    public List<DoSEntity1> getQuery5(@Param("emp1") String emp1, @Param("emp2") String emp2);
}