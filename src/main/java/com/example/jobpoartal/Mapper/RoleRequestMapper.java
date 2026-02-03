package com.example.jobpoartal.Mapper;

import com.example.jobpoartal.Dto.RoleRequestDto;
import com.example.jobpoartal.Dto.RoleResponseDto;
import com.example.jobpoartal.Entity.RoleRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleRequestMapper {

//    @Mapping(target = "requestRole", source = "requestRole")
//    @Mapping(target = "companyName", source = "companyName")
//    @Mapping(target = "companyEmail", source = "companyEmail")
//    @Mapping(target = "companyWebsite", source = "companyWebsite")
//    @Mapping(target = "reason", source = "reason")
    RoleRequest toentity(RoleRequestDto dto);
//
//    @Mapping(target = "requestRole", source = "requestRole")
//    @Mapping(target = "companyName", source = "companyName")
//    @Mapping(target = "companyEmail", source = "companyEmail")
//    @Mapping(target = "companyWebsite", source = "companyWebsite")
//    @Mapping(target = "reason", source = "reason")
    RoleResponseDto toDto(RoleRequest entity);
}





