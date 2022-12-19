package com.dbms.prj2.service;

import com.dbms.prj2.entity.*;
import com.dbms.prj2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryService {

    @Autowired
    private maxAchieverRepository maxAcheiverRepository;
    public List<maxAchieverEntity> getMaxAchievers() { return  maxAcheiverRepository.getMaxAchievers(); }
    @Autowired
    private fastestLapsRepository fastestLapsRepository;
    public List<fastestLapsEntity> getFastestLaps(){
        return fastestLapsRepository.getFastestLaps();
    }

    @Autowired
    private constructorWinRepository constructorWinRepository;
    public List<constructorWinEntity> getconstructorWin() { return constructorWinRepository.getconstructorWin();}

    @Autowired
    private winnerDistinctRepository winnerDistinctRepository;
    public List<winnerDistinctEntity> getWinnerDistinct() {
        return winnerDistinctRepository.getWinnerDistinct();
    }


}
