package com.example.jobpoartal.Entity;

import com.example.jobpoartal.Enum.JobStatus;
import jakarta.persistence.*;

@Entity
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  private   Long id;
private String name;
private String  email;
private String Resume;

@Enumerated(EnumType.STRING)
private JobStatus jobStatus;



    @ManyToOne
    private Users users;

    @ManyToOne
    private Job jobs;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
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

