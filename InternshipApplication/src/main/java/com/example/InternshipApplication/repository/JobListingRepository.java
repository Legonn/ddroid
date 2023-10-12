package com.example.InternshipApplication.repository;

import com.example.InternshipApplication.model.JobListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobListingRepository extends JpaRepository<JobListing,Integer> {
}
