package com.dbms.prj2.repository;

import com.dbms.prj2.entity.fastestLapsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface fastestLapsRepository extends JpaRepository<fastestLapsEntity, Integer> {

    @Query(value = "SELECT DISTINCT RR.raceId AS race_id, D.driverId AS driver_id, CONCAT(D.forename, \" \", D.surname) AS Name, RR.name AS championship, RR.fastestLapSpeed AS fastest_lap_speed FROM (SELECT FL.raceId, FL.driverId, FL.fastestLapSpeed, Ra.name FROM (SELECT Re.raceId, Re.driverId, Re.fastestLapSpeed FROM results Re where fastestLapSpeed > 250) FL JOIN races Ra ON Ra.raceId = FL.raceId ) RR JOIN drivers D ON D.driverId = RR.driverId;", nativeQuery = true)
    public List<fastestLapsEntity> getFastestLaps();
}