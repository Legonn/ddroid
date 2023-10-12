package com.example.InternshipApplication.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Table(name="jobListings")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int openPositions;
    private String title;
    private String jobDescription;
    @ManyToMany
    @JoinTable(
            name="jobListingApplicants",
            joinColumns = @JoinColumn(name="jobListing_id"),
            inverseJoinColumns = @JoinColumn (name="applicant_id")
    )
    private Set<Applicant> jobListingApplicants;

    public JobListing(int openPositions, String title, String jobDescription, Set<Applicant> jobListingApplicants) {
        this.openPositions = openPositions;
        this.title = title;
        this.jobDescription = jobDescription;
        this.jobListingApplicants = jobListingApplicants;
    }

    @Override
    public String toString() {
        return "JobListing{" +
                "id=" + id +
                ", openPositions=" + openPositions +
                ", title='" + title + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", jobListingApplicants=" + jobListingApplicants +
                '}';
    }
}
