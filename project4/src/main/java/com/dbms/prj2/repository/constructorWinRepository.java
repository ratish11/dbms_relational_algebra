package com.dbms.prj2.repository;

import com.dbms.prj2.entity.constructorWinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface constructorWinRepository extends JpaRepository<constructorWinEntity, Integer > {

    @Query(value="SELECT R.raceId AS race_id, A.constructorId AS constructor_id, A.Max_Points, R.name FROM ( SELECT constructorId, raceId,  MAX(points) AS Max_Points from constructor_results GROUP BY raceId ) A JOIN races R WHERE A.raceId=R.raceId GROUP BY R.year;", nativeQuery = true)
    public List<constructorWinEntity> getconstructorWin();
}

