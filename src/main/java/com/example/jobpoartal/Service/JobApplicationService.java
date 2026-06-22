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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
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

    // Automatically inject your custom configured S3Client bean
    @Autowired
    private S3Client s3Client;

    // Only inject the bucket name string here
    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    // Upload file directly to AWS S3
    public JobApplicationResponseDto UploadResume(Long job_id, JobApplicationRequestDto application, MultipartFile file, Userdetails principal) throws IOException {

        Users users = principal.getUsers();
        Users data = userRepository.findById(users.getId()).orElseThrow(() -> new RuntimeException("user not found "));
        Job job = jobRepository.findById(job_id).orElseThrow(() -> new RuntimeException("job is not found"));

        boolean alreadyApplied = jobApplication.existsByUsersIdAndJobsId(data.getId(), job.getId());
        if (alreadyApplied) {
            throw new RuntimeException("You have already applied to this job.");
        }

        // Generate unique filename for the S3 object key
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        // Upload directly from the file's input stream to S3
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(filename)
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

        // Map to entity
        JobApplication application1 = jobApplicationMapper.toentity(application);
        application1.setResume(filename); // Store the S3 object key (filename) in your DB
        application1.setUsers(data);
        application1.setJobs(job);
        application1.setJobStatus(JobStatus.APPLIED);

        // Save in DB
        JobApplication savedApplication = jobApplication.save(application1);

        // Return DTO
        return jobApplicationMapper.todto(savedApplication);
    }

    // Download byte arrays directly from AWS S3
    public byte[] download(long id, Userdetails principal) throws IOException {
        Users user = principal.getUsers();
        userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("invalid user"));

        JobApplication job = jobApplication.findById(id).orElseThrow(() -> new RuntimeException("Error application not found"));
        String filename = job.getResume();

        try {
            // Retrieve file from S3 bucket as a byte array
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(filename)
                    .build();

            return s3Client.getObjectAsBytes(getObjectRequest).asByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Failed to download file from S3 storage: " + e.getMessage());
        }
    }

    // Used to see job status
    public List<JobApplicationResponseDto> seeJobStatus(Userdetails principal) {
        Users loggedUser = principal.getUsers();
        List<JobApplication> jobs = jobApplication.findAllByUsersId(loggedUser.getId());

        if (jobs.isEmpty()) {
            throw new RuntimeException("No job applications found");
        }

        return jobs.stream()
                .map(jobApplicationMapper::todto)
                .toList();
    }

    // Jobs from the admin
    public List<JobcreatingResponseDto> JobsFromAdmin(String location, String type) {
        List<Job> job = jobRepository.findByLocationAndType(location, type);
        return job.stream()
                .map(jobCreatingMapper::todto)
                .toList();
    }
}
