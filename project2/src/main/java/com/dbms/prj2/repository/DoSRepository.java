package com.dbms.prj2.repository;

import com.dbms.prj2.entity.DoSEntity1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface DoSRepository extends JpaRepository {

    @Query(value = "SELECT D1.emp_no AS e1, D2.emp_no AS e2 FROM dept_emp D1 JOIN dept_emp D2 ON (D1.dept_no = D2.dept_no) AND (((D1.from_date <= D2.from_date) AND (D1.to_date <= D2.to_date)) OR((D1.from_date >= D2.from_date) AND (D1.to_date >= D2.to_date)) OR ((D1.from_date >= D2.from_date) AND (D1.to_date <= D2.to_date)) OR ((D1.from_date <= D2.from_date) AND (D1.to_date>=D2.to_date))) WHERE NOT D1.emp_no = D2.emp_no AND D1.emp_no = :emp1 AND D2.emp_no = :emp2;", nativeQuery = true)
    public List<DoSEntity1> getQuery5(@Param("emp1") Integer emp1, @Param("emp2") Integer emp2);

//    @Query(value = "SELECT DISTINCT(FMGR.Name) FROM (SELECT FE.emp_no,FE.birth_date, CONCAT(FE.last_name, \" \", FE.first_name) AS Name, FE.gender FROM (SELECT * FROM employees WHERE gender='F' AND  birth_date<'1990-01-01') FE JOIN dept_manager DE ON FE.emp_no = DE.emp_no) FMGR JOIN salaries S ON FMGR.emp_no = S.emp_no WHERE S.salary>80000;", nativeQuery = true)
}