package com.jobApp.firstjobapp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {

    private JobService jobService;
    // dependency injection via controller
    // loosely coupled
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob( @RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job created" , HttpStatus.CREATED);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> findJobById(@PathVariable Long id){
        Job job = jobService.getJobById(id);
        if(job == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(job);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        boolean delete = jobService.deleteJobById(id);
        if(delete){
            return new ResponseEntity<>("Job deleted" , HttpStatus.OK);
        }
        return new ResponseEntity<>("Job not found" , HttpStatus.NOT_FOUND);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<String> updateJobById(@RequestBody Job job, @PathVariable Long id){
        boolean updated = jobService.updateJobById(job,id);
        if(updated){
            return new ResponseEntity<>("Job updated" , HttpStatus.OK);
        }
        return new ResponseEntity<>("Job not found" , HttpStatus.NOT_FOUND);
    }

}
