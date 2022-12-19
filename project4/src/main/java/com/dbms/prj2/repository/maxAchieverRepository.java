package com.dbms.prj2.repository;

import com.dbms.prj2.entity.maxAchieverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface maxAchieverRepository extends JpaRepository<maxAchieverEntity, Integer> {

    @Query(value = "SELECT CONCAT(D.forename, \" \", D.surname) As Name, MAX(Re.points) AS Max_Points, D.driverId AS driver_id, Re.constructorId AS constructor_id, Ra.year FROM results Re JOIN races Ra JOIN drivers D where Ra.raceId=Re.raceId AND D.driverId=Re.driverId group by Ra.year ORDER BY Ra.year DESC;", nativeQuery = true)
    public List<maxAchieverEntity> getMaxAchievers();
}
