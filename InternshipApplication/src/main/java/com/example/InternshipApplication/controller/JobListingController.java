package com.example.InternshipApplication.controller;


import com.example.InternshipApplication.model.Applicant;
import com.example.InternshipApplication.model.DTO.ApplicantDTO;
import com.example.InternshipApplication.model.JobListing;
import com.example.InternshipApplication.service.ApplicantService;
import com.example.InternshipApplication.service.JobListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class JobListingController {

    public ApplicantService applicantService;
    public JobListingService jobListingService;

    @Autowired
    public JobListingController(ApplicantService applicantService, JobListingService jobListingService) {
        this.applicantService = applicantService;
        this.jobListingService = jobListingService;
    }

    @GetMapping("/viewAllJobListings")
    public String viewAllJobListings(Model model){
        List<JobListing> jobListingList=jobListingService.findAll();
        model.addAttribute("jobListingList",jobListingList);
        return "display-jobs";
    }


    @GetMapping("/displayApplyForm")
    public String displayApplyForm(@RequestParam ("jobListingId") int id,Model model){
        ApplicantDTO applicantDTO=new ApplicantDTO();
        model.addAttribute("jobListingId",id);
        model.addAttribute("applicantDTO",applicantDTO);
        return "display-apply-form";
    }

    @PostMapping("/processApplyForm")
    public String applyForJobListing(@RequestParam ("jobListingId") int id,@ModelAttribute("applicantDTO") ApplicantDTO applicantDTO){
        applicantService.createApplicant(id,applicantDTO);
        return "redirect:/congratulations";
    }

    @GetMapping("/showApplicantsForJobListing")
    public String showApplicantsForJobListing(@RequestParam ("jobListingId") int id,Model model){
        List<Applicant> applicants= jobListingService.getAllApplicantsForJobListing(id);
        model.addAttribute("applicants",applicants);

        return "display-applicants";
    }
}


