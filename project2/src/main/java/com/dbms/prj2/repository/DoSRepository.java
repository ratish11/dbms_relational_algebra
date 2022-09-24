package com.dbms.prj2.repository;


import com.dbms.prj2.entity.DoSEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoSRepository extends JpaRepository {

    @Query(value = "SELECT D1.emp_no AS emp1, D2.emp_no AS emp2 FROM dept_emp D1 JOIN dept_emp D2 ON (D1.dept_no = D2.dept_no) AND (((D1.from_date <= D2.from_date) AND (D1.to_date <= D2.to_date)) OR((D1.from_date >= D2.from_date) AND (D1.to_date >= D2.to_date)) OR ((D1.from_date >= D2.from_date) AND (D1.to_date <= D2.to_date)) OR ((D1.from_date <= D2.from_date) AND (D1.to_date>=D2.to_date))) WHERE NOT D1.emp_no = D2.emp_no AND D1.emp_no = :emp1 AND D2.emp_no = :emp2;", nativeQuery = true)
    public List<DoSEntity> getQuery5(@Param("emp1") String emp1, @Param("emp2") String emp2);
}
