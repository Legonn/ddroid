package com.example.InternshipApplication.controller;

import com.example.InternshipApplication.model.Applicant;
import com.example.InternshipApplication.model.DTO.JobListingDTO;
import com.example.InternshipApplication.model.JobListing;
import com.example.InternshipApplication.model.User;
import com.example.InternshipApplication.service.EmployerService;
import com.example.InternshipApplication.service.JobListingService;
import com.example.InternshipApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/employer")
public class EmployerController {

    private JobListingService jobListingService;
    private EmployerService employerService;
    private UserService userService;

    @Autowired
    public EmployerController(JobListingService jobListingService, EmployerService employerService, UserService userService) {
        this.jobListingService = jobListingService;
        this.employerService = employerService;
        this.userService = userService;
    }

    @GetMapping("/createJobListingForm")
    public String createJobListing(Model model,@RequestParam("employerId") int employerId){
        JobListingDTO jobListing =new JobListingDTO();
        model.addAttribute("jobListingDTO",jobListing);
        model.addAttribute("employerId",employerId);
        return "create-job-listing";
    }

    @PostMapping("/createJobListing")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createJobListing(@RequestParam("employerId") int id, @ModelAttribute("jobListing") JobListingDTO jobListingDTO){
        jobListingService.createJobListing(id,jobListingDTO);
    }

    @GetMapping("/jobListings")
    public String displayJobListingForAnEmployer(@RequestParam("employerId") int id, Model model, Principal principal){
        List<JobListing> jobListingList=employerService.findJobListingsForEmployer(id);
        if (principal !=null){
            User user=userService.findUserByUserName(principal.getName());
            model.addAttribute("user",user);
        }
        model.addAttribute("jobListingList",jobListingList);
        model.addAttribute("employerId",id);

        return "display-jobs";
    }

    @GetMapping("/getAllApplicants")
    public String getAllApplicants(@RequestParam("employerId") int id, Model model){
        List<Applicant> applicants=employerService.getAllApplicantsForEmployer(id);

        model.addAttribute("applicants",applicants);

        return "display-applicants";
    }

    @DeleteMapping("/removeJobListing")
    public String removeJobListing(@RequestParam ("jobListingId") int jobListingId,@RequestParam("employerId") int employerId){
        jobListingService.removeJobListing(jobListingId,employerId);
        return "redirect:/employer/jobListings?employerId="+employerId;

    }


}
