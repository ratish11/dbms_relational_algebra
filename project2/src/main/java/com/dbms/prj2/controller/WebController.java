package com.dbms.prj2.controller;

import com.dbms.prj2.entity.EmpEntity;
import com.dbms.prj2.entity.Query3Entity;
import com.dbms.prj2.entity.SalaryEntity;
import com.dbms.prj2.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
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
    @GetMapping("/")
    public String home(){
        return "index";
    }
}
