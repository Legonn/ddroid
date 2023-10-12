package com.example.InternshipApplication.service;


import com.example.InternshipApplication.model.Applicant;
import com.example.InternshipApplication.model.Employer;
import com.example.InternshipApplication.model.JobListing;
import com.example.InternshipApplication.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EmployerServiceImpl implements EmployerService{

    private EmployerRepository employerRepository;


    @Autowired
    public EmployerServiceImpl(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    @Override
    public List<JobListing> findJobListingsForEmployer(int id) {
        Employer employer=employerRepository.findById(id).orElseThrow(()->new RuntimeException("Employer Not Found"));
        List<JobListing> jobListingList=new ArrayList<>();
        if (employer.getJobListingSet().size()>=1){
            jobListingList.addAll(employer.getJobListingSet());
        }
        return jobListingList;
    }

    @Override
    public List<Applicant> getAllApplicantsForEmployer(int id) {
        Employer employer=employerRepository.findById(id).orElseThrow(()->new RuntimeException("Employer not Found"));
        List<Applicant> applicants=new ArrayList<>();
        employer.getJobListingSet().forEach(jobListing ->{
            if (jobListing.getJobListingApplicants() !=null){
                applicants.addAll(jobListing.getJobListingApplicants());
            }
        });
        return applicants;
    }
}
