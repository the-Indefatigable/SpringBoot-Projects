package com.alam.jobapplication.job;

import java.util.List;

public interface JobService {
  List<Job> findAll();

  void createJob(Job job);

  Job findById(long id);

  boolean deleteJob(long id);

  boolean updateJob(long id, Job job);
}