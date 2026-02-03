package com.example.jobpoartal.Mapper;


import com.example.jobpoartal.Dto.SavedJobRequestDto;
import com.example.jobpoartal.Dto.SavedJobResponseDto;
import com.example.jobpoartal.Entity.SavedJobs;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SavedJobMapper {

    SavedJobs toentity(SavedJobRequestDto dto);
    SavedJobResponseDto todto(SavedJobs entity);
}
