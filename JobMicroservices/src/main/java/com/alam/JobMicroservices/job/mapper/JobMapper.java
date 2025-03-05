package com.alam.JobMicroservices.job.mapper;

import com.alam.JobMicroservices.job.Job;
import com.alam.JobMicroservices.job.dto.JobDto;
import com.alam.JobMicroservices.job.external.Company;
import com.alam.JobMicroservices.job.external.Review;

import java.util.List;

public class JobMapper {

    public static JobDto mapToDto(Job job , Company company , List<Review> reviews) {
        JobDto jobDto = new JobDto();
        jobDto.setId(job.getId());
        jobDto.setName(job.getName());
        jobDto.setDescription(job.getDescription());
        jobDto.setCompany(company);
        jobDto.setMaxSalary(job.getMaxSalary());
        jobDto.setMinSalary(job.getMinSalary());
        jobDto.setLocation(job.getLocation());
        jobDto.setReviewList(reviews);
        return jobDto;
    }

}
