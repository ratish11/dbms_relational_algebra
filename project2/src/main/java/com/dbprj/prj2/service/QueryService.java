package com.dbprj.prj2.service;

import com.dbprj.prj2.entity.empDetails;
import com.dbprj.prj2.repo.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QueryService  {
    @Autowired
    private EmpRepository empRepository;

    public void saveQueryData(empDetails empdetails){
        empRepository.save(empdetails);
    }
    public List<empDetails> getAllData(){
        return empRepository.findAll();
    }

    public Optional<empDetails> getDataByID(int id){
        return empRepository.findById(id);
    }

    public List<empDetails> getNativeResults(){
        return empRepository.getNativeResults();
    }

}
