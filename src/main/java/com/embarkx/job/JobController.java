package com.embarkx.job;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {
	private JobService jobService;
	
	
	public JobController(JobService jobService) {
		
		this.jobService = jobService;
	}

	@GetMapping("/jobs")
	public ResponseEntity<List<Job>> findAll(){
		return ResponseEntity.ok(jobService.findAll());
	}
	@PostMapping("/jobs")
	public ResponseEntity<String> createJob(@RequestBody Job job) {
		jobService.createJob(job);
		return new ResponseEntity<>("Job Added Successfully",HttpStatus.CREATED);
	}
	@GetMapping("/jobs/{id}")
	public ResponseEntity<Job> getJobById(@PathVariable Long id) {
		Job job = jobService.getJobById(id);
		if(job!=null) 
			return new  ResponseEntity<>(job, HttpStatus.OK);

	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	@DeleteMapping("/jobs/{id}")
	public ResponseEntity<String> deleteJob(@PathVariable Long id){
		boolean deleted = jobService.deleteJob(id);
		if(deleted) 
			return new ResponseEntity<>("Job Deleted Successfully", HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	@PutMapping("/jobs/{id}")
	public ResponseEntity<String> updateJob(@PathVariable Long id,@RequestBody Job updatedJob){
		boolean updated =jobService.updatedJob(id, updatedJob);
		if(updated)
			return new ResponseEntity<>("Job Updated Successfully", HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}

