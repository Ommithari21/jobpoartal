package com.example.jobpoartal.Dto;

import com.example.jobpoartal.Entity.Users;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public class SavedJobRequestDto {

    private Long JobId;

    private String JobTitle;

    private String CompanyName;

    private String Location;

    private String JobUrl;

    private LocalDateTime SavedAt;

    private Boolean isApplied=false;


    @ManyToOne
    private Users users;

    public Long getJobId() {
        return JobId;
    }

    public void setJobId(Long jobId) {
        JobId = jobId;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(String jobTitle) {
        JobTitle = jobTitle;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getJobUrl() {
        return JobUrl;
    }

    public void setJobUrl(String jobUrl) {
        JobUrl = jobUrl;
    }

    public LocalDateTime getSavedAt() {
        return SavedAt;
    }

    public void setSavedAt(LocalDateTime savedAt) {
        SavedAt = savedAt;
    }

    public Boolean getApplied() {
        return isApplied;
    }

    public void setApplied(Boolean applied) {
        isApplied = applied;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
