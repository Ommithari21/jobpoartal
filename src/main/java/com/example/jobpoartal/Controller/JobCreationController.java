package com.example.jobpoartal.Controller;

import com.example.jobpoartal.Dto.JobcreatingResponseDto;
import com.example.jobpoartal.Dto.JobcreatingRquestDto;
import com.example.jobpoartal.Entity.Job;
import com.example.jobpoartal.Security.Userdetails;
import com.example.jobpoartal.Service.JobCreationService;
import jakarta.validation.Valid;
import org.mapstruct.control.MappingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/create")
public class JobCreationController {

    @Autowired
    private JobCreationService jobCreationService;


    @PostMapping("/createjob")
    public ResponseEntity<JobcreatingResponseDto>CreateJob(@Valid @RequestBody JobcreatingRquestDto job, @AuthenticationPrincipal Userdetails userdetails){
        return ResponseEntity.ok(jobCreationService.createjob(job,userdetails));

    }

    @PutMapping("/update_job/{id}")
    public ResponseEntity<JobcreatingResponseDto>UpdateJob(@PathVariable Long id,@Valid @RequestBody JobcreatingRquestDto job,@AuthenticationPrincipal Userdetails principal){
        return ResponseEntity.ok(jobCreationService.updateJob(id,job,principal));

    }

    @DeleteMapping("/delete_job/{id}")
    public ResponseEntity<String>deletejob(@PathVariable Long id){
        jobCreationService.DeleteJob(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/Get_all_jobs")
    public ResponseEntity<List<JobcreatingResponseDto>> findJob(@AuthenticationPrincipal  Userdetails Principal){
        return ResponseEntity.ok(jobCreationService.GetAllJob(Principal));
    }


}
