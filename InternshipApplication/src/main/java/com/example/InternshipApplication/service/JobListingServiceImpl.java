package com.example.InternshipApplication.service;

import com.example.InternshipApplication.model.Applicant;
import com.example.InternshipApplication.model.Employer;
import com.example.InternshipApplication.model.JobListing;
import com.example.InternshipApplication.model.DTO.JobListingDTO;
import com.example.InternshipApplication.repository.EmployerRepository;
import com.example.InternshipApplication.repository.JobListingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class JobListingServiceImpl implements JobListingService{

    private JobListingRepository jobListingRepository;
    private EmployerRepository employerRepository;

    @Autowired
    public JobListingServiceImpl(JobListingRepository jobListingRepository, EmployerRepository employerRepository) {
        this.jobListingRepository = jobListingRepository;
        this.employerRepository = employerRepository;
    }




    @Override
    @Transactional
    public void createJobListing(int employerId, JobListingDTO jobListingDTO) {
        if(validateJobListingDTOData(jobListingDTO)){
            JobListing jobListing=JobListing.builder()
                    .title(jobListingDTO.getTitle())
                    .jobDescription(jobListingDTO.getDescription())
                    .openPositions(jobListingDTO.getOpenPositions())
                    .jobListingApplicants(new HashSet<>())
                    .build();
            Employer employer=employerRepository.findById(employerId).orElseThrow(()->new RuntimeException("Employer not Found"));

            if (!employer.getJobListingSet().contains(jobListing)){
                employer.getJobListingSet().add(jobListing);
                employerRepository.save(employer);
            }
        }
    }

    public boolean validateJobListingDTOData(JobListingDTO jobListingDTO){
        if (jobListingDTO.getDescription().isBlank() || jobListingDTO.getDescription().length()<10){
            return false;
        } else if (jobListingDTO.getTitle().isBlank()) {
            return false;
        } else if (jobListingDTO.getOpenPositions()<=0) {
            return false;
        }
        return true;
    }

    @Override
    public List<JobListing> findAll() {
        return jobListingRepository.findAll();
    }

    @Override
    public List<Applicant> getAllApplicantsForJobListing(int id) {
        JobListing jobListing=jobListingRepository.findById(id).orElseThrow(()->new RuntimeException("Job Listing not found"));
        List<Applicant> applicants=new ArrayList<>(jobListing.getJobListingApplicants());
        return applicants;
    }

    @Override
    @Transactional
    public void removeJobListing(int jobListingId,int employerId) {
        Employer employer=employerRepository.findById(employerId).orElseThrow(()->new RuntimeException("Employer not found"));
        JobListing jobListing=jobListingRepository.findById(jobListingId).orElseThrow(()->new RuntimeException("Job Listing Not Found"));
        employer.getJobListingSet().removeIf(jobListing1->jobListing1.equals(jobListing));
        employerRepository.save(employer);
        jobListingRepository.delete(jobListing);
    }
}
