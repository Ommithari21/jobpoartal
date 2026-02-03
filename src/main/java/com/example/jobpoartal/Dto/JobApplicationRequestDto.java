package com.example.jobpoartal.Dto;

import com.example.jobpoartal.Entity.Job;
import com.example.jobpoartal.Entity.Users;
import com.example.jobpoartal.Enum.JobStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

public class JobApplicationRequestDto {


    private String name;
    private String  email;

    private String Resume;

    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;


    @ManyToOne
    private Users users;

    @ManyToOne
    private Job jobs;

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

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Job getJobs() {
        return jobs;
    }

    public void setJobs(Job jobs) {
        this.jobs = jobs;
    }
}
