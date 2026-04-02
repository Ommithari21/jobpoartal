package com.example.jobpoartal.Mapper;

import com.example.jobpoartal.Dto.UserUpdateRequetDto;
import com.example.jobpoartal.Dto.UserUpdateResponseDto;
import com.example.jobpoartal.Entity.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UpdateUserMapper {
    Users toentity(UserUpdateRequetDto dto);
    UserUpdateResponseDto todto(Users entity);
}
