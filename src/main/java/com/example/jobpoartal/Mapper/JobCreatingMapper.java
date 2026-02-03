package com.example.jobpoartal.Mapper;

import com.example.jobpoartal.Dto.JobcreatingResponseDto;
import com.example.jobpoartal.Dto.JobcreatingRquestDto;
import com.example.jobpoartal.Entity.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JobCreatingMapper {

    Job toentity (JobcreatingRquestDto dto);

    @Mapping(source = "id", target = "job_id")
    JobcreatingResponseDto todto(Job entity) ;


}