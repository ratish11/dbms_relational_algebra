package com.dbms.prj2.controller;

import com.dbms.prj2.entity.*;
import com.dbms.prj2.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/requestData")
@CrossOrigin(origins = "http://localhost:3000") //this is the only change that i had to do
public class WebController {

    @Autowired
    private QueryService queryService;

    @GetMapping("/query1")
    public List<maxAchieverEntity> getMaxAchievers(){
        return queryService.getMaxAchievers();
    }
    @GetMapping("/query2")
    public List<fastestLapsEntity> getFastestLaps(){
        return queryService.getFastestLaps();
    }
    @GetMapping("/query3")
    public List<constructorWinEntity> getconstructorWin()
    {
        return queryService.getconstructorWin();
    }
    @GetMapping("/query4")
    public List<winnerDistinctEntity> getWinnerDistinct(){
        return queryService.getWinnerDistinct();
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }
}
