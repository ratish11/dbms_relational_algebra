package com.dbms.prj2.repository;

import com.dbms.prj2.entity.winnerDistinctEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface winnerDistinctRepository extends JpaRepository<winnerDistinctEntity, String> {

    @Query(value = "SELECT CONCAT(D.forename, \" \", D.surname) As Name, D.driverId AS driver_id, R.constructorId AS constructor_id FROM (SELECT T1.constructorId, T1.raceId, T1.Max_Points FROM (SELECT constructorId, raceId, MAX(points) AS Max_Points FROM constructor_results GROUP BY raceId) T1 JOIN ( SELECT resultId, driverId, constructorId, raceId FROM results WHERE position = \"1\" ) T2 WHERE T1.raceId = T2.raceId AND T1.constructorId != T2.constructorId) R JOIN drivers D;", nativeQuery = true)
    public List<winnerDistinctEntity> getWinnerDistinct();
}
