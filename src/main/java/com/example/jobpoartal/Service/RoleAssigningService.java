package com.example.jobpoartal.Service;

import com.example.jobpoartal.Dto.RoleResponseDto;
import com.example.jobpoartal.Enum.ROLES;
import com.example.jobpoartal.Entity.RoleRequest;
import com.example.jobpoartal.Enum.Status;
import com.example.jobpoartal.Entity.Users;
import com.example.jobpoartal.Mapper.RoleRequestMapper;
import com.example.jobpoartal.Repositories.RoleRequestRepository;
import com.example.jobpoartal.Repositories.UserRepository;
import com.example.jobpoartal.Security.Userdetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleAssigningService {

    private final UserRepository userRepository;
    private final RoleRequestRepository roleRequestRepository;
    private RoleRequestMapper roleRequestMapper;

    @Autowired
    public RoleAssigningService(UserRepository userRepository, RoleRequestRepository roleRequestRepository, RoleRequestMapper roleRequestMapper) {
        this.userRepository = userRepository;
        this.roleRequestRepository = roleRequestRepository;
        this.roleRequestMapper = roleRequestMapper;
    }



    public String approveRole(Long requestId, Userdetails principal) {

        // 1. Load role request
        RoleRequest request = roleRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Role request not found"));


System.out.print("role requestid"+request.getUser().getId());
        Users user = request.getUser();

        // 2. Update user role
//user.setRoles(Collections.singletonList(ROLES.ADMIN));
        user.getRoles().clear();
        user.getRoles().add(ROLES.USER);
        user.getRoles() .add(ROLES.ADMIN);
    request.setStatus(Status.APPROVED);

        userRepository.save(user);
      roleRequestRepository.save(request);


        // 3. Delete or mark request as approved
     //   roleRequestRepository.delete(request);

        return "The role is updated sucessfully";
    }

/// for rejecting the role
    public String rejectRole(Long requestId,Userdetails principal) {

        // 1. Load role request
        RoleRequest request = roleRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Role request not found"));



        // 2. Mark as rejected (user role does NOT change)
        request.setStatus(Status.REJECTED);

        // 3. Save updated request status
         roleRequestRepository.save(request);
         return "The role is rejected sucessfully";

    }
    public List<RoleResponseDto> viewRequest(){
        return  roleRequestRepository.findAll()
                .stream().map(roleRequestMapper::toDto)
                .collect(Collectors.toList());



    }
}
