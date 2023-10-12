package com.example.InternshipApplication.service;

import com.example.InternshipApplication.model.Applicant;
import com.example.InternshipApplication.model.DTO.JobListingDTO;
import com.example.InternshipApplication.model.JobListing;

import java.util.List;


public interface JobListingService {

    void createJobListing(int employerId, JobListingDTO jobListingDTO);
    boolean validateJobListingDTOData(JobListingDTO jobListingDTO);
    List<JobListing> findAll();
    List<Applicant> getAllApplicantsForJobListing(int id);
    void removeJobListing(int jobListingId,int employerId);
}
