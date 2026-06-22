package com.example.jobpoartal.Controller;

import com.example.jobpoartal.Dto.JobApplicationRequestDto;
import com.example.jobpoartal.Dto.JobApplicationResponseDto;
import com.example.jobpoartal.Dto.JobcreatingResponseDto;
import com.example.jobpoartal.Entity.Job;
import com.example.jobpoartal.Entity.JobApplication;
import com.example.jobpoartal.Repositories.JobApplicationRepository;
import com.example.jobpoartal.Security.Userdetails;
import com.example.jobpoartal.Service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/job")
public class JobapplicationController
{
    @Autowired
    private JobApplicationService jobApplicationService;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @PostMapping( value="/apply/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<String>apply(@PathVariable Long id,
                                               @RequestPart("data") JobApplicationRequestDto application,
                                               @RequestPart("resume")MultipartFile resume,
                                               @AuthenticationPrincipal Userdetails principal
                                               )throws Exception
    {
        jobApplicationService.UploadResume(id,application,resume,principal);
        return ResponseEntity.ok().body("you have sucesfully applied");


    }

    @GetMapping("/applications/{id}/resume")
    public ResponseEntity<byte[]>download(@PathVariable long id,@AuthenticationPrincipal Userdetails principal) throws IOException {
            byte[] FileBytes = jobApplicationService.download(id,principal);
            JobApplication job=jobApplicationRepository.findById(id).orElseThrow(()->new RuntimeException("error"));
            String filename= job.getResume();

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header("Content-Disposition", "attachment; filename=\"" + filename + "\"")
                    .body(FileBytes);


    }

    @GetMapping("/view_status")
    public ResponseEntity<List<JobApplicationResponseDto>>view(@AuthenticationPrincipal Userdetails principal){
        return ResponseEntity.ok(jobApplicationService.seeJobStatus(principal));

    }

    @GetMapping("/admin/Jobs")
    public ResponseEntity<List<JobcreatingResponseDto>>jobs(@RequestParam  String location,@RequestParam String type){
        return ResponseEntity.ok(jobApplicationService.JobsFromAdmin(location, type));
    }

}
