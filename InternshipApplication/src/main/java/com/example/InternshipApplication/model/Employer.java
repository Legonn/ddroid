package com.example.InternshipApplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Table(name="employers")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String companyName;
    @OneToMany(cascade =CascadeType.ALL,fetch = FetchType.EAGER)
    Set<JobListing> jobListingSet;


    public Employer(String companyName, Set<JobListing> jobListingSet) {
        this.companyName = companyName;
        this.jobListingSet = jobListingSet;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", jobListingSet=" + jobListingSet +
                '}';
    }
}
