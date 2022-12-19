package com.dbms.prj2.controller;
import java.io.*;

import com.dbms.prj2.entity.*;
import com.dbms.prj2.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    public List<SalaryEntity> getQuery1(){
        return queryService.getQuery1();
    }
    @GetMapping("/query2")
    public List<EmpEntity> getQuery2(){
        return queryService.getQuery2();
    }
    @GetMapping("/query3")
    public  List<Query3Entity> getQuery3()
    {
        return queryService.getQuery3();
    }
    @GetMapping("/query4")
    public List<EmpEntity> getQuery4(){
        return queryService.getQuery4();
    }

    @PostMapping("/query5/{emp1}/{emp2}")
    public List<DoSEntity1> query5(@PathVariable String emp1, @PathVariable String emp2){
        return queryService.getQuery5(emp1,emp2);
    }

    @PostMapping("/query6/{emp1}/{emp2}")
    public  List<DoS2Entity> query6(@PathVariable String emp1, @PathVariable String emp2) {
        return  queryService.getQuery6(emp1, emp2);
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }
}
