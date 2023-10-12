package com.example.InternshipApplication.service;

import com.example.InternshipApplication.model.Applicant;
import com.example.InternshipApplication.model.JobListing;
import com.example.InternshipApplication.repository.EmployerRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployerService {

    List<JobListing> findJobListingsForEmployer(int id);
    List<Applicant> getAllApplicantsForEmployer(int id);
}
