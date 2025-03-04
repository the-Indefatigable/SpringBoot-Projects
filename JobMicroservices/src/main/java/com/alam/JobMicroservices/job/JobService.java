package com.alam.JobMicroservices.job;

import com.alam.JobMicroservices.job.dto.JobWithCompanyDto;

import java.util.List;

public interface JobService {
  List<JobWithCompanyDto> findAll();

  void createJob(Job job);

  Job findById(long id);

  boolean deleteJob(long id);

  boolean updateJob(long id, Job job);
}