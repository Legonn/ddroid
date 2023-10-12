package com.example.InternshipApplication.service;

import com.example.InternshipApplication.model.DTO.ApplicantDTO;

public interface ApplicantService {


    void createApplicant(int jobListingId, ApplicantDTO applicantDTO);
    boolean validateApplicantDTOData(ApplicantDTO applicantDTO);
}
