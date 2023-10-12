package com.example.InternshipApplication.service;

import com.example.InternshipApplication.model.Applicant;
import com.example.InternshipApplication.model.DTO.ApplicantDTO;
import com.example.InternshipApplication.model.JobListing;
import com.example.InternshipApplication.repository.ApplicantRepository;
import com.example.InternshipApplication.repository.JobListingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Service;

@Service
public class ApplicantServiceImpl implements ApplicantService{

    private ApplicantRepository applicantRepository;
    private JobListingRepository jobListingRepository;

    @Autowired
    public ApplicantServiceImpl(ApplicantRepository applicantRepository, JobListingRepository jobListingRepository) {
        this.applicantRepository = applicantRepository;
        this.jobListingRepository = jobListingRepository;
    }

    @Override
    @Transactional
    public void createApplicant(int jobListingId, ApplicantDTO applicantDTO) {
        if (validateApplicantDTOData(applicantDTO)){
            Applicant applicant=Applicant.builder()
                    .firstName(applicantDTO.getFirstName())
                    .lastName(applicantDTO.getLastName())
                    .email(applicantDTO.getEmail())
                    .phoneNumber(applicantDTO.getPhoneNumber())
                    .address1(applicantDTO.getAddress1())
                    .address2(applicantDTO.getAddress2())
                    .state(applicantDTO.getState())
                    .country(applicantDTO.getCountry())
                    .city(applicantDTO.getCity())
                    .build();
            applicantRepository.save(applicant);
            JobListing jobListing=jobListingRepository.findById(jobListingId).orElseThrow(()->new RuntimeException("Job listing not found"));
            if (!jobListing.getJobListingApplicants().contains(applicant)){
                jobListing.getJobListingApplicants().add(applicant);
                jobListingRepository.save(jobListing);
            }
        }

    }

    @Override
    public boolean validateApplicantDTOData(ApplicantDTO applicantDTO) {
        if (applicantDTO.getFirstName().isBlank() || applicantDTO.getFirstName().length()<2 || !Character.isUpperCase(applicantDTO.getFirstName().charAt(0))){
            return false;
        } else if (applicantDTO.getLastName().isBlank() || applicantDTO.getLastName().length()<2 || !Character.isUpperCase(applicantDTO.getLastName().charAt(0))) {
            return false;
        } else if (!applicantDTO.getEmail().contains("@")) {
            return false;
        } else if (applicantDTO.getAddress1().isBlank()) {
            return false;
        } else if (applicantDTO.getPhoneNumber().length() !=10 ) {
            return false;
        }
        return true;
    }
}
