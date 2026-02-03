package com.example.jobpoartal.Mapper;

import com.example.jobpoartal.Dto.UserRequestDto;
import com.example.jobpoartal.Dto.UserResponseDto;
import com.example.jobpoartal.Entity.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    Users toentity(UserRequestDto dto);
    UserResponseDto todto(Users entity);
}
