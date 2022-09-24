package com.dbms.prj2.controller;

import com.dbms.prj2.entity.DoSEntity;
import com.dbms.prj2.entity.EmpEntity;
import com.dbms.prj2.entity.Query3Entity;
import com.dbms.prj2.entity.SalaryEntity;
import com.dbms.prj2.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/requestData")
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
    public List<DoSEntity> query5(@PathVariable String emp1, @PathVariable String emp2){
        return queryService.getQuery5(emp1,emp2);
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }
}
