package com.example.jobpoartal.Service;

import com.example.jobpoartal.Dto.JobApplicationRequestDto;
import com.example.jobpoartal.Dto.JobApplicationResponseDto;
import com.example.jobpoartal.Dto.JobcreatingResponseDto;
import com.example.jobpoartal.Entity.Job;
import com.example.jobpoartal.Entity.JobApplication;
import com.example.jobpoartal.Entity.Users;
import com.example.jobpoartal.Enum.JobStatus;
import com.example.jobpoartal.Mapper.JobApplicationMapper;
import com.example.jobpoartal.Mapper.JobCreatingMapper;
import com.example.jobpoartal.Repositories.JobApplicationRepository;
import com.example.jobpoartal.Repositories.JobRepository;
import com.example.jobpoartal.Repositories.UserRepository;
import com.example.jobpoartal.Security.Userdetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepository jobApplication;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobApplicationMapper jobApplicationMapper;
@Autowired
    private JobCreatingMapper jobCreatingMapper;


    @Autowired
    private JobRepository jobRepository;


    private final String uploddir="C:\\jobpoartal\\uploads\\";


    //upload the file
    public JobApplicationResponseDto UploadResume(Long job_id, JobApplicationRequestDto application, MultipartFile file, Userdetails principal) throws IOException {

        Users users=principal.getUsers();

        Users data=userRepository.findById(users.getId()).orElseThrow(()->new RuntimeException("user not found "));

        Job job= jobRepository.findById(job_id).orElseThrow(()->new RuntimeException("job is not found"));

        boolean alreadyApplied = jobApplication.existsByUsersIdAndJobsId(data.getId(), job.getId());
        if (alreadyApplied) {
            throw new RuntimeException("You have already applied to this job.");
        }


        // create uploads folder if not exists
        Path path = Paths.get(uploddir);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

// generate unique filename
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();

// resolve the full path in uploads folder
//        Path filepath = path.resolve(filename);
//        Files.copy(file.getInputStream(), filepath); // this  is of Files utility class

        file.transferTo(new File(uploddir+filename));

// map to entity
        JobApplication application1 = jobApplicationMapper.toentity(application);
        application1.setResume(filename); // <-- only filename
        application1.setUsers(data);
        application1.setJobs(job);
        application1.setJobStatus(JobStatus.APPLIED);

// save in DB
        JobApplication savedApplication = jobApplication.save(application1);

// return DTO
        return jobApplicationMapper.todto(savedApplication);

    }

    //for download the file
    public byte[] download(long id,Userdetails principal) throws IOException{
    Users user=principal.getUsers();
    Users data=userRepository.findById(user.getId()).orElseThrow(()->new RuntimeException("invalid user"));
    JobApplication job=jobApplication.findById(id).orElseThrow(()->new RuntimeException("Error "));
  //  List<JobApplication> jobApplication1=jobApplication.findByJobId();

        String filename=job.getResume();

        Path filepath=Paths.get(uploddir).resolve(filename);

        if(!Files.exists(filepath)){
            throw new RuntimeException("error");

        }
        return Files.readAllBytes(filepath);
    }

    //used to see job status
    public List<JobApplicationResponseDto> seeJobStatus(Userdetails principal) {

        Users loggedUser = principal.getUsers();

        List<JobApplication> jobs =
                jobApplication.findAllByUsersId(loggedUser.getId());

        if (jobs.isEmpty()) {
            throw new RuntimeException("No job applications found");
        }

        return jobs.stream()
                .map(jobApplicationMapper::todto)
                .toList();
    }


    //jobs from the admin
    public List<JobcreatingResponseDto>JobsFromAdmin(String location,String type){
List<Job>job=jobRepository.findByLocationAndType(location, type);

        return job.stream()
                .map(jobCreatingMapper::todto)
                .toList();


    }

    }
