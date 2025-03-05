package com.alam.JobMicroservices.job;
import com.alam.JobMicroservices.job.dto.JobDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<JobDto> findAll() {
        return jobService.findAll();
    }



    @GetMapping("/{id}")
    public ResponseEntity<JobDto> findById(@PathVariable long id) {
        if (jobService.findById(id) != null) {
            return new ResponseEntity<> (jobService.findById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public String createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return "Job added Successfully";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>  deleteJob(@PathVariable long id) {
        boolean deleted = jobService.deleteJob(id);
        if (deleted) {
            return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Job Not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updateJob/{id}")
    public ResponseEntity<String> updateJob(@PathVariable long id, @RequestBody Job job) {
        boolean updated = jobService.updateJob( id ,  job);
        if (updated) {
            return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Job Not found", HttpStatus.NOT_FOUND);
    }
}


