package com.dbms.prj2.service;

import com.dbms.prj2.entity.EmpEntity;
import com.dbms.prj2.entity.Query3Entity;
import com.dbms.prj2.entity.SalaryEntity;
import com.dbms.prj2.repository.EmpRepository;
import com.dbms.prj2.repository.Query3Repository;
import com.dbms.prj2.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryService {
    @Autowired
    private EmpRepository empRepository;
    public List<EmpEntity> getQuery2(){
        return empRepository.getQuery2();
    }
    public List<EmpEntity> getQuery4(){
        return empRepository.getQuery4();
    }

    @Autowired
    private SalaryRepository salaryRepository;
    public List<SalaryEntity> getQuery1() { return  salaryRepository.getQuery1(); }

    @Autowired
    private Query3Repository query3Repository;
    public  List<Query3Entity> getQuery3() { return query3Repository.getQuery3();}
}
