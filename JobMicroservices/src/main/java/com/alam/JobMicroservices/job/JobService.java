package com.alam.JobMicroservices.job;

import com.alam.JobMicroservices.job.dto.JobDto;

import java.util.List;

public interface JobService {
  List<JobDto> findAll();

  void createJob(Job job);

  JobDto findById(long id);

  boolean deleteJob(long id);

  boolean updateJob(long id, Job job);
}