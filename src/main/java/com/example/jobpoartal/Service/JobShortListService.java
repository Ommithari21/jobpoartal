package com.example.jobpoartal.Service;

import com.example.jobpoartal.Dto.JobApplicationResponseDto;
import com.example.jobpoartal.Entity.JobApplication;
import com.example.jobpoartal.Enum.JobStatus;
import com.example.jobpoartal.Mapper.JobApplicationMapper;
import com.example.jobpoartal.Repositories.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobShortListService {


//  private JobApplication jobApplication;
//private JobApplicationMapper jobApplicationMapper;

    private JobApplicationService jobApplicationService;
    private JobApplicationRepository jobApplicationRepository;
    private  JobApplicationMapper  jobApplicationMapper;

    @Autowired
    public JobShortListService(JobApplicationService jobApplicationService,
                               JobApplicationRepository jobApplicationRepository,
                               JobApplicationMapper jobApplicationMapper) {
        this.jobApplicationService = jobApplicationService;
        this.jobApplicationRepository = jobApplicationRepository;
        this.jobApplicationMapper = jobApplicationMapper;
    }

    public JobApplicationResponseDto JobReject(Long JobApplicationId){
   JobApplication job=jobApplicationRepository.findById(JobApplicationId).orElseThrow(()->new RuntimeException("error"));
   job.setJobStatus(JobStatus.REJECTED);

   JobApplication set=jobApplicationRepository.save(job);
  return  jobApplicationMapper.todto(set);
}

public JobApplicationResponseDto JobShortlist(Long JobApplicationId){
    JobApplication job=jobApplicationRepository.findById(JobApplicationId).orElseThrow(()->new RuntimeException("erroe"));
    job.setJobStatus(JobStatus.SHORTLISTED);
    JobApplication set=jobApplicationRepository.save(job);
    return jobApplicationMapper.todto(set);

}


}
