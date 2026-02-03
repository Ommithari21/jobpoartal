package com.example.jobpoartal.Dto;

import com.example.jobpoartal.Enum.JobStatus;

public class JobApplicationResponseDto {

    private String name;
    private String  email;
    private String Resume;
    private JobStatus jobStatus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResume() {
        return Resume;
    }

    public void setResume(String resume) {
        Resume = resume;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }
}
