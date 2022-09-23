package com.dbprj.prj2.repo;

import com.dbprj.prj2.entity.empDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface EmpRepository extends JpaRepository<empDetails, Integer> {
//    @Query("select dept_name from departments")
//    public List<empDetails> getQuery1Data();

    @Query(value="select * from departments join dept_manager", nativeQuery = true)
    public List<empDetails> getNativeResults();

}