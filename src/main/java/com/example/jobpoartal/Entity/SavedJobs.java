package com.example.jobpoartal.Entity;

import jakarta.persistence.*;

        import java.time.LocalDateTime;

@Entity
public class SavedJobs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long JobId;

    private String JobTitle;

    private String CompanyName;

    private String Location;

    private String JobUrl;

    private LocalDateTime SavedAt;

    private Boolean isApplied=false;


    @ManyToOne
    private Users users;


    public SavedJobs() {}


    public SavedJobs( Long jobId, String jobTitle, String companyName, String location, String jobUrl,
                     LocalDateTime savedAt, Boolean isApplied, Users users) {

        JobId = jobId;
        JobTitle = jobTitle;
        CompanyName = companyName;
        Location = location;
        JobUrl = jobUrl;
        SavedAt = savedAt;
        this.isApplied = isApplied;
        this.users = users;
    }


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
