package com.example.jobpoartal.Dto;

public class SavedJobResponseDto {
    private String JobTitle;

    private String CompanyName;

    private String Location;

    private String JobUrl;


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
}
