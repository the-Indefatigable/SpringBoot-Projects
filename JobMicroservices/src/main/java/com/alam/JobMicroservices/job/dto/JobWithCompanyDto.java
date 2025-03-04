package com.alam.JobMicroservices.job.dto;

import com.alam.JobMicroservices.job.Job;
import com.alam.JobMicroservices.job.external.Company;

public class JobWithCompanyDto {
    private Job job;
    private Company company;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
