package com.example.jobpoartal.Mapper;

import com.example.jobpoartal.Dto.JobApplicationRequestDto;
import com.example.jobpoartal.Dto.JobApplicationResponseDto;
import com.example.jobpoartal.Entity.JobApplication;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobApplicationMapper {

    JobApplication toentity(JobApplicationRequestDto dto);
    JobApplicationResponseDto todto(JobApplication entity);



}
