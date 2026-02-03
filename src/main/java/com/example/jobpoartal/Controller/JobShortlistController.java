package com.example.jobpoartal.Controller;

import com.example.jobpoartal.Dto.JobApplicationRequestDto;
import com.example.jobpoartal.Dto.JobApplicationResponseDto;
import com.example.jobpoartal.Service.JobApplicationService;
import com.example.jobpoartal.Service.JobShortListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application_status")
public class JobShortlistController {

private JobShortListService jobShortListService;

@Autowired
    public JobShortlistController(JobShortListService jobShortListService) {
        this.jobShortListService = jobShortListService;
    }

    @PutMapping("/shortlist/{jobid}")
    public ResponseEntity<JobApplicationResponseDto>shortlist(@PathVariable Long jobid)
    {
        return ResponseEntity.ok(jobShortListService.JobShortlist(jobid));

    }

    @PutMapping("/rejected/{id}")
    public ResponseEntity<JobApplicationResponseDto>rejected(@PathVariable Long id){
        return ResponseEntity.ok(jobShortListService.JobReject(id));

    }

}
