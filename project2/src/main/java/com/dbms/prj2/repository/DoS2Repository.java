package com.dbms.prj2.repository;

import com.dbms.prj2.entity.DoS2Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoS2Repository extends JpaRepository<DoS2Entity, Integer> {

    @Query(value = "SELECT DOS1.e1_emp, DOS1.e1_dept, DOS1.e1_from, DOS1.e1_to, DOS1.e3_emp, DOS1.e3_dept, DOS1.e3_from, DOS1.e3_to, DOS2.e2_emp, DOS2.e2_dept, DOS2.e2_from, DOS2.e2_to FROM (SELECT D1.emp_no AS e1_emp, D1.dept_no AS e1_dept, D1.from_date AS e1_from, D1.to_date AS e1_to, D3.emp_no AS e3_emp, D3.dept_no AS e3_dept, D3.from_date AS e3_from, D3.to_date AS e3_to FROM dept_emp D1 JOIN dept_emp D3 ON D1.dept_no=D3.dept_no AND (((D1.from_date<=D3.from_date) AND ((D1.to_date>=D3.from_date) OR (D1.to_date <= D3.to_date))) OR ((D3.from_date<=D1.from_date) AND ((D3.to_date>=D1.from_date) OR (D3.to_date <= D1.to_date))) OR ((D1.from_date >= D3.from_date) AND (D1.to_date <= D3.to_date)) OR ((D3.from_date >= D1.from_date) AND (D3.to_date <= D1.to_date))) WHERE D1.emp_no != D3.emp_no) DOS1 JOIN (SELECT D3.emp_no AS e3_emp, D3.dept_no AS e3_dept, D3.from_date AS e3_from, D3.to_date AS e3_to, D2.emp_no AS e2_emp, D2.dept_no AS e2_dept, D2.from_date AS e2_from, D2.to_date AS e2_to FROM dept_emp D2 JOIN dept_emp D3 ON D2.dept_no=D3.dept_no AND (((D2.from_date<=D3.from_date) AND ((D2.to_date>=D3.from_date) OR (D2.to_date <= D3.to_date))) OR ((D3.from_date<=D2.from_date) AND ((D3.to_date>=D2.from_date) OR (D3.to_date <= D2.to_date))) OR ((D2.from_date >= D3.from_date) AND (D2.to_date <= D3.to_date)) OR ((D3.from_date >= D2.from_date) AND (D3.to_date <= D2.to_date))) WHERE D2.emp_no != D3.emp_no) DOS2 ON DOS1.e3_emp = DOS2.e3_emp AND DOS1.e1_emp != DOS2.e2_emp AND DOS1.e3_dept != DOS2.e3_dept AND DOS1.e1_emp = :emp1 AND DOS2.e2_emp = :emp2 LIMIT 100;", nativeQuery = true)
    public List<DoS2Entity> getQuery6(@Param("emp1") String emp1, @Param("emp2") String emp2);
}
