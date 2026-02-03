package com.example.jobpoartal.Api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ApiResponse {

    private List<Job> jobs;
    private int totalCount;
    private Location location;
    private CompanyData companyData;

    public List<Job> getJobs() { return jobs; }
    public void setJobs(List<Job> jobs) { this.jobs = jobs; }

    public int getTotalCount() { return totalCount; }
    public void setTotalCount(int totalCount) { this.totalCount = totalCount; }

    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }

    public CompanyData getCompanyData() { return companyData; }
    public void setCompanyData(CompanyData companyData) { this.companyData = companyData; }

    public static class Job {
        @JsonProperty("job_title")
        private String jobTitle;
        @JsonProperty("company_name")
        private String companyName;
        @JsonProperty("application_link")
        private String applicationLink;
        @JsonProperty("job_type")
        private String jobType;
        @JsonProperty("location_type")
        private String locationType;
        @JsonProperty("date_posted")
        private String datePosted;
        private String description;
        @JsonProperty("requirements_summary")
        private String requirementsSummary;
        @JsonProperty("job_categories")
        private List<String> jobCategories;
        private List<Location> locations;
        private CompanyData companyData;
        public String getJobTitle() { return jobTitle; }
        public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

        public String getCompanyName() { return companyName; }
        public void setCompanyName(String companyName) { this.companyName = companyName; }

        public String getApplicationLink() { return applicationLink; }
        public void setApplicationLink(String applicationLink) { this.applicationLink = applicationLink; }

        public String getJobType() { return jobType; }
        public void setJobType(String jobType) { this.jobType = jobType; }

        public String getLocationType() { return locationType; }
        public void setLocationType(String locationType) { this.locationType = locationType; }

        public String getDatePosted() { return datePosted; }
        public void setDatePosted(String datePosted) { this.datePosted = datePosted; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public String getRequirementsSummary() { return requirementsSummary; }
        public void setRequirementsSummary(String requirementsSummary) { this.requirementsSummary = requirementsSummary; }

        public List<String> getJobCategories() { return jobCategories; }
        public void setJobCategories(List<String> jobCategories) { this.jobCategories = jobCategories; }

        public List<Location> getLocations() { return locations; }
        public void setLocations(List<Location> locations) { this.locations = locations; }

        public CompanyData getCompanyData() { return companyData; }
        public void setCompanyData(CompanyData companyData) { this.companyData = companyData; }
    }

    public static class Location {
        private String city;
        private String region;
        private String country;
        private String address;

        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }

        public String getRegion() { return region; }
        public void setRegion(String region) { this.region = region; }

        public String getCountry() { return country; }
        public void setCountry(String country) { this.country = country; }

        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }
    }

    public static class CompanyData {
        private String descriptionSummary;
        private String linkedinLink;
        private List<String> industries;

        public String getDescriptionSummary() { return descriptionSummary; }
        public void setDescriptionSummary(String descriptionSummary) { this.descriptionSummary = descriptionSummary; }

        public String getLinkedinLink() { return linkedinLink; }
        public void setLinkedinLink(String linkedinLink) { this.linkedinLink = linkedinLink; }

        public List<String> getIndustries() { return industries; }
        public void setIndustries(List<String> industries) { this.industries = industries; }
    }
}
