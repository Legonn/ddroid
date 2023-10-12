package com.example.InternshipApplication.service;


import com.example.InternshipApplication.model.Applicant;
import com.example.InternshipApplication.model.Employer;
import com.example.InternshipApplication.model.JobListing;
import com.example.InternshipApplication.repository.ApplicantRepository;
import com.example.InternshipApplication.repository.EmployerRepository;
import com.example.InternshipApplication.repository.JobListingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class SampleDataServiceImpl implements SampleDataService{
    private EmployerRepository employerRepository;
    private ApplicantRepository applicantRepository;
    private JobListingRepository jobListingRepository;

    @Autowired
    public SampleDataServiceImpl(EmployerRepository employerRepository, ApplicantRepository applicantRepository, JobListingRepository jobListingRepository) {
        this.employerRepository = employerRepository;
        this.applicantRepository = applicantRepository;
        this.jobListingRepository = jobListingRepository;
    }

    @Override
    public List<Employer> createSampleData() {
        Employer employer1=new Employer("ddroid",new HashSet<>());
        Employer employer2=new Employer("Facebook",new HashSet<>());
        employerRepository.save(employer1);
        employerRepository.save(employer2);


        JobListing jobListing1=new JobListing(5,"Java Developer Internship","Java Developer Internship Description",new HashSet<>());
        JobListing jobListing2=new JobListing(7,"C# Developer Internship","C# Developer Internship Description",new HashSet<>());
        JobListing jobListing3=new JobListing(3,"FullStack Developer Internship","FullStack Developer Internship Description",new HashSet<>());
        JobListing jobListing4=new JobListing(2,"Front-End Developer Internship","Front-End Developer Internship Description",new HashSet<>());

        JobListing jobListing5=new JobListing(5,"Java Developer Internship1","Java Developer Internship Description",new HashSet<>());
        JobListing jobListing6=new JobListing(7,"C# Developer Internship1","C# Developer Internship Description",new HashSet<>());
        JobListing jobListing7=new JobListing(3,"FullStack Developer Internship1","FullStack Developer Internship Description",new HashSet<>());
        JobListing jobListing8=new JobListing(2,"Front-End Developer Internship1","Front-End Developer Internship Description",new HashSet<>());

        setEmployerForJobListing(employer1, jobListing1, jobListing2, jobListing3, jobListing4);
        setEmployerForJobListing(employer2,jobListing5,jobListing6,jobListing7,jobListing8);



        generateApplicants(employer1);
        generateApplicants(employer2);



            return List.of(employer1,employer2);

    }

    public List<Employer> findAllEmployers(){
       return employerRepository.findAll();
    }

    @Transactional
    private void setEmployerForJobListing(Employer employer, JobListing jobListing1, JobListing jobListing2, JobListing jobListing3, JobListing jobListing4) {
        List.of(jobListing1, jobListing2, jobListing2, jobListing3, jobListing4).forEach(jobListing -> {
            employer.getJobListingSet().add(jobListing);
        });
    }

    @Transactional
    private void generateApplicants(Employer employer) {
        for (int i=0;i<10;i++){
            List<String> names=new ArrayList<>(List.of("Alex","Traian","George","Marian","Ghita"));
            List<String> cities=new ArrayList<>(List.of("Timisoara","Cluj","Bucuresti"));
            String firstName=names.get(new Random().nextInt(4));
            String lastName=names.get(new Random().nextInt(4));
            Applicant applicant=Applicant.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .phoneNumber(0775646321+i+"")
                    .email(firstName+"@"+lastName+".com")
                    .address1("address1")
                    .address2("address2")
                    .country("Romania")
                    .city(cities.get(new Random().nextInt(3)))
                    .state(null)
                    .build();
            applicantRepository.save(applicant);
            for (JobListing jobListing: employer.getJobListingSet()){
                if (jobListing.getJobListingApplicants().size()<2 && i<8){
                    jobListing.getJobListingApplicants().add(applicant);
                } else if (i>7 && jobListing.getJobListingApplicants().size()<3) {
                    jobListing.getJobListingApplicants().add(applicant);

                }
            }
        }
    }
}
