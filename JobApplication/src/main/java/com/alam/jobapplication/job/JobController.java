package com.alam.jobapplication.job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public List<Job> findAll() {
        return jobService.findAll();
    }

    // All end points
    // All jobs
    //  by id
    // post jobs
    // delete job by id
    // put job by id
    // get company by job id
//  @GetMapping("/jobs/{id}")
//  public Job findById(@PathVariable long id) {
//      if (jobService.findById(id) != null) {
//        return jobService.findById(id);
//       }
//      return null;
//
//    //above one is correct, but we want more control over the response code
//    // and what we show as the post request
//  }

    //new findby id method using response entitiy

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> findById(@PathVariable long id) {
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

    //below is the correct but not the thing that we want the user to show
//  @DeleteMapping("/delete/{id}")
//  public String removeJob(@PathVariable long id) {
//    jobService.deleteJob(id);
//    return "job successfully removed";

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


