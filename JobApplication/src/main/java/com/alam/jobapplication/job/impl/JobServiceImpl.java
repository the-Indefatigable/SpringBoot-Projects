package com.alam.jobapplication.job.impl;

import com.alam.jobapplication.job.Job;
import com.alam.jobapplication.job.JobRepository;
import com.alam.jobapplication.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

//    private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;
//    long id = 0;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
//        return jobs;
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
//        job.setId(id++);
//        jobs.add(job);
        jobRepository.save(job);

    }

    @Override
    public Job findById(long id) {
//        for (Job job : jobs) {
//            if(job.getId() == id){
//                return job;
//            }
//        }
//
//        return null;
        return jobRepository.findById(id).orElse(null);
    }
    //do the same thing using iterator<> class;
    @Override
    public boolean deleteJob(long id) {
//       for (Job job : jobs) {
//           if(job.getId() == id){
//               jobs.remove(job);
//               return true;
//
//           }
//       }
//
//       return false;
        try {
            jobRepository.deleteById(id);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean updateJob(long id , Job job) {

//        for (Job job1 : jobs) {
//            if(job1.getId() == id){
//                job1.setName(job.getName());
//                job1.setDescription(job.getDescription());
//                job1.setLocation(job.getLocation());
//                job1.setMaxSalary(job.getMaxSalary());
//                job1.setMinSalary(job.getMinSalary());
//                return true;
//            }
//        }
//
//
//        return false;
//    }

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
