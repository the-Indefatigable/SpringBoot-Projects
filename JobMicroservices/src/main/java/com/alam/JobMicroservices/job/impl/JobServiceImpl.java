package com.alam.JobMicroservices.job.impl;

import com.alam.JobMicroservices.job.Job;
import com.alam.JobMicroservices.job.JobRepository;
import com.alam.JobMicroservices.job.JobService;
import com.alam.JobMicroservices.job.clients.CompanyClient;
import com.alam.JobMicroservices.job.clients.ReviewClient;
import com.alam.JobMicroservices.job.dto.JobDto;
import com.alam.JobMicroservices.job.external.Company;
import com.alam.JobMicroservices.job.external.Review;
import com.alam.JobMicroservices.job.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

//    private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;

//    long id = 0;
//    @Autowired
//    RestTemplate restTemplate;
//    @Autowired
    private final CompanyClient companyClient;
//    @Autowired
    private final ReviewClient reviewClient;

    public JobServiceImpl(JobRepository jobRepository , CompanyClient companyClient, ReviewClient reviewClient) {
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
        this.jobRepository = jobRepository;
    }

    @Override
//    @CircuitBreaker(name ="companyBreaker" , fallbackMethod = "companyBreakerFallBack")
    @RateLimiter(name = "companyBreaker" , fallbackMethod = "companyBreakerFallBack")
    public List<JobDto> findAll() {
        List<Job> jobs = jobRepository.findAll();

        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<String> companyBreakerFallBack(Exception e) {
        List<String> str = new ArrayList<>();
        str.add("dummy");
        return str;
    }

    private JobDto convertToDto(Job job) {
    //this is using conventional RestTEmpalate
//            Company company = restTemplate.getForObject("http://COMPANYMICROSERVICE:8081/companies/" + job.getCompanyId(), Company.class );
//
//
//            //we did not use getFor method cuz it is usefull when you have a generic collection
//           ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http://REVIEWMICROSERVICE:8083/reviews?companyId=" + job.getCompanyId(), HttpMethod.GET,null,new ParameterizedTypeReference<List<Review>>() {});

        Company company = companyClient.getCompany(job.getCompanyId());

           //we have to get the body
//           List<Review> reviews = reviewResponse.getBody();
            List<Review> reviews = reviewClient.getReview(job.getCompanyId());
            return JobMapper.mapToDto(job, company,reviews);

    }


    @Override
    public void createJob(Job job) {
//        job.setId(id++);
//        jobs.add(job);
        jobRepository.save(job);

    }

    @Override
    public JobDto findById(long id) {

        Job job = jobRepository.findById(id).orElse(null);
        if (job == null) {
            return null;
        }
        return convertToDto(job);
    }
    //do the same thing using iterator<> class;
    @Override
    public boolean deleteJob(long id) {

        try {
            jobRepository.deleteById(id);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean updateJob(long id , Job job) {

        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job jobToUpdate = jobOptional.get();
            jobToUpdate.setName(job.getName());
            jobToUpdate.setDescription(job.getDescription());
            jobToUpdate.setLocation(job.getLocation());
            jobToUpdate.setMaxSalary(job.getMaxSalary());
            jobToUpdate.setMinSalary(job.getMinSalary());
            jobRepository.save(jobToUpdate);
            return true;
        }
        return false;
    }
}
