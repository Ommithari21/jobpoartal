package com.example.jobpoartal.Controller;

import com.example.jobpoartal.Dto.JobApplicationResponseDto;
import com.example.jobpoartal.Service.JobAssigningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class JobViewController {

    private final String uploddir="uploads/";



    @Autowired
    public JobAssigningService jobApplicationService;

    @GetMapping("/jobs")
    public ResponseEntity<List<JobApplicationResponseDto>>jobapplication(){
        return ResponseEntity.ok(jobApplicationService.GetAllApplications());
    }


    @GetMapping("/data/{job_id}")
    public ResponseEntity<List<JobApplicationResponseDto>>getapplications( @PathVariable Long job_id){
        return ResponseEntity.ok(jobApplicationService.GetPerticularJobApplications(job_id));

    }






}
