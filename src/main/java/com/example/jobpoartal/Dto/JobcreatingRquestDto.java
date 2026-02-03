package com.example.jobpoartal.Dto;

import jakarta.validation.constraints.NotNull;

public class JobcreatingRquestDto {

    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String location;
@NotNull
private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
