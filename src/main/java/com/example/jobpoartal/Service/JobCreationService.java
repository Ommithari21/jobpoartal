package com.example.jobpoartal.Service;

import com.example.jobpoartal.Dto.JobcreatingResponseDto;
import com.example.jobpoartal.Dto.JobcreatingRquestDto;
import com.example.jobpoartal.Entity.Job;
import com.example.jobpoartal.Entity.Users;
import com.example.jobpoartal.Mapper.JobCreatingMapper;
import com.example.jobpoartal.Repositories.JobRepository;
import com.example.jobpoartal.Repositories.UserRepository;
import com.example.jobpoartal.Security.Userdetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobCreationService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
private JobCreatingMapper jobCreatingMapper;


    public JobcreatingResponseDto createjob(JobcreatingRquestDto jobdto, Userdetails data){

        Users logeduser=data.getUsers();

        Job job=jobCreatingMapper.toentity(jobdto);
        job.setUser(logeduser);

        Job value=jobRepository.save(job);
        return jobCreatingMapper.todto(value);
    }




    public JobcreatingResponseDto updateJob(Long id,JobcreatingRquestDto job,Userdetails principal){
       Job findjob=jobRepository.findById(id).orElseThrow(()-> new RuntimeException("the job not found "));
        Users loggedUser = principal.getUsers();

//        Job existingJob = jobRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Job not found"));


        if (!findjob.getUser().getId().equals(loggedUser.getId())) {
            throw new RuntimeException("You are not allowed to update this job");
        }



         findjob.setDescription(job.getDescription());
         findjob.setLocation(job.getLocation());
         findjob.setTitle(job.getTitle());
         findjob.setType(job.getType());

        Job data= jobRepository.save(findjob);
         return jobCreatingMapper.todto(data);


    }

    public String DeleteJob(Long id){
        jobRepository.deleteById(id);
        return " job deleted SuccessFully";

    }

    public List<JobcreatingResponseDto>GetAllJob(Userdetails principal){

        Users logeduser=principal.getUsers();
        List<Job>jobs=jobRepository.findByUserId(logeduser.getId());
          return jobs.stream().map(jobCreatingMapper::todto).collect(Collectors.toList());


    }



}
