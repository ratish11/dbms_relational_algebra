package com.dbms.prj2.repository;

import com.dbms.prj2.entity.SalaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalaryRepository extends JpaRepository<SalaryEntity, String> {

    @Query(value = "SELECT fin.dept_name, fin.ratio FROM (SELECT Male.dept_name,(Fmale.fsal/Male.msal) AS ratio FROM ((SELECT dept_name, avg(salary) AS fsal, gender FROM departments JOIN dept_emp ON departments.dept_no = dept_emp.dept_no JOIN employees ON dept_emp.emp_no = employees.emp_no JOIN salaries ON employees.emp_no = salaries.emp_no GROUP BY dept_name,gender HAVING gender = 'F') Fmale,(SELECT dept_name, avg(salary) AS msal, gender FROM  departments JOIN dept_emp ON departments.dept_no = dept_emp.dept_no JOIN employees ON dept_emp.emp_no = employees.emp_no JOIN salaries ON employees.emp_no = salaries.emp_no GROUP BY dept_name,gender HAVING gender = 'M') Male) WHERE Male.dept_name = Fmale.dept_name) fin WHERE fin.ratio = (SELECT MAX(final.ratio) FROM (SELECT Male.dept_name,(Fmale.fsal/Male.msal) AS ratio FROM ((SELECT dept_name, avg(salary) AS fsal, gender FROM departments JOIN dept_emp ON departments.dept_no = dept_emp.dept_no JOIN employees ON dept_emp.emp_no = employees.emp_no JOIN salaries ON employees.emp_no = salaries.emp_no GROUP BY dept_name,gender HAVING gender = 'F') Fmale,(SELECT dept_name, avg(salary) AS msal, gender FROM  departments JOIN dept_emp ON departments.dept_no = dept_emp.dept_no JOIN employees ON dept_emp.emp_no = employees.emp_no JOIN salaries ON employees.emp_no = salaries.emp_no GROUP BY dept_name,gender HAVING gender = 'M') Male) WHERE Male.dept_name = Fmale.dept_name) final );", nativeQuery = true)
    public List<SalaryEntity> getQuery1();
}
