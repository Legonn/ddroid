package com.example.InternshipApplication.controller;

import com.example.InternshipApplication.model.Employer;
import com.example.InternshipApplication.service.SampleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class SampleDataController {

    private final SampleDataService sampleDataService;

    @Autowired
    public SampleDataController(SampleDataService sampleDataService) {
        this.sampleDataService = sampleDataService;
    }

    @PostMapping("/generate")
    @ResponseStatus(HttpStatus.CREATED)
    public void generateSampleData(){
        List<Employer> employers= sampleDataService.createSampleData();
    }

    @GetMapping("/displayContent")
    @ResponseBody
    public String displayContent(){
        sampleDataService.findAllEmployers().forEach(employer -> System.out.println(employer));
        return "Success";
    }

}
