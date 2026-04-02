package com.example.jobpoartal.Service;

import com.example.jobpoartal.Dto.JobApplicationResponseDto;
import com.example.jobpoartal.Entity.JobApplication;
import com.example.jobpoartal.Mapper.JobApplicationMapper;
import com.example.jobpoartal.Repositories.JobApplicationRepository;
import com.example.jobpoartal.Repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobAdminViewService {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
  private  JobApplicationMapper jobApplicationMapper;

//get all applications
    public List<JobApplicationResponseDto> GetAllApplications(){
        return jobApplicationRepository.findAll().stream().
                map(jobApplicationMapper::todto).collect(Collectors.toList());

    }

    // get only the job_id applications
    public List<JobApplicationResponseDto> GetPerticularJobApplications( Long job_id){

              List<JobApplication> jobApplication=jobApplicationRepository.findByJobId(job_id);
              return jobApplication.stream().map(jobApplicationMapper::todto).collect(Collectors.toList());

    }

}






