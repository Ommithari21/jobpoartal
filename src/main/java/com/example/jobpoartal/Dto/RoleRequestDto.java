package com.example.jobpoartal.Dto;


import jakarta.validation.constraints.Email;

public class RoleRequestDto {
    private String name;
    private String message;
    private String requestRole;

    private String companyName;

    @Email(regexp = "^(?![_.-])(?!.*[_.-]{2})[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    private String companyEmail;

    private String reason;
    private String companyWebsite;


    public String getRequestRole() {
        return requestRole;
    }

    public void setRequestRole(String requestRole) {
        this.requestRole = requestRole;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
