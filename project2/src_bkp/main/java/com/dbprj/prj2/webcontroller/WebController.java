package com.dbprj.prj2.webcontroller;

import com.dbprj.prj2.entity.empDetails;
import com.dbprj.prj2.repo.EmpRepository;
import com.dbprj.prj2.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping("/requestData")
public class WebController {
    @Autowired
    private QueryService queryService;

    @PostMapping("/addData")
    public String addData(@RequestBody empDetails empdetails){
        queryService.saveQueryData(empdetails);
        return "saved Data successfully";
    }

    @GetMapping("/getQueryData")
    public List<empDetails> getAllData(){
        return queryService.getAllData();
    }
    @GetMapping("/getDataByID/{id}")
    public Optional<empDetails> getDataByID(@PathVariable int id){
        return queryService.getDataByID(id);
    }

    @GetMapping("/getNativeResults")
    public List<empDetails> getNativeResults(){
        return queryService.getNativeResults();
    }
    @GetMapping("/")
    public String home(){
        return "index";
    }


    @PostMapping("/poll")
    public String poll(@ModelAttribute empDetails e, HttpSession session){

        System.out.println(e);

//        repostyr.save(e);
//        session.setAttribute("message", "accepted");
        return "redirect:/";
    }
}
