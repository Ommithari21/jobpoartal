package com.example.jobpoartal.Dto;

import com.example.jobpoartal.Enum.Status;

public class RoleResponseDto {

    private String name;
    private Status status;
    private String requestRole;
    private String companyName;
    private String companyEmail;
    private String reason;

    private String companyWebsite;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getRequestRole() {
        return requestRole;
    }

    public void setRequestRole(String requestRole) {
        this.requestRole = requestRole;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }
}
