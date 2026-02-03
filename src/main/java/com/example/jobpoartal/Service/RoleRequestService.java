package com.example.jobpoartal.Service;

import com.example.jobpoartal.Dto.RoleRequestDto;
import com.example.jobpoartal.Dto.RoleResponseDto;
import com.example.jobpoartal.Entity.RoleRequest;
import com.example.jobpoartal.Enum.Status;
import com.example.jobpoartal.Entity.Users;
import com.example.jobpoartal.Mapper.RoleRequestMapper;
import com.example.jobpoartal.Repositories.RoleRequestRepository;
import com.example.jobpoartal.Repositories.UserRepository;
import com.example.jobpoartal.Security.Userdetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleRequestService {

        @Autowired
        private RoleRequestRepository roleRequestRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private RoleRequestMapper roleRequestMapper;



        public RoleResponseDto SendRequest(RoleRequestDto request, Userdetails principal){
          //  Users user= userRepository.findById(request.getUserid()).orElseThrow(()->new RuntimeException("user not found "));

       //   Optional< RoleRequest> request1=roleRequestRepository.findById(request.getUserid());
//            if(request1.isPresent()) {
//throw new RuntimeException("The request is already submitted ");
//
//            }
                Users logeduser=principal.getUsers();
                RoleRequest role = roleRequestMapper.toentity(request);
                role.setStatus(Status.PENDING);
//                role.setName(request.getName());
//                role.setRequestRole(request.getRequestRole());
//                role.setCompanyEmail(request.getCompanyEmail());
//                role.setCompanyName(request.getCompanyName());
//                role.setCompanyWebsite(request.getCompanyWebsite());
//                role.setReason(request.getReason());
                role.setUser(logeduser);
                System.out.print(request.getRequestRole());

                RoleRequest data = roleRequestRepository.save(role);


                return roleRequestMapper.toDto(data);

            }



      public RoleResponseDto roleStatus(Userdetails principal){
                Users currentUsersRequest=principal.getUsers();
              //  RoleRequest roleRequest=roleRequestRepository.findById(roleRequestId).orElseThrow(()->new RuntimeException("invalid user"));
     RoleRequest roleRequest1=roleRequestRepository.findByUserId((currentUsersRequest.getId()));



              if (!roleRequest1.getUser().getId().equals(currentUsersRequest.getId())) {
                      throw new RuntimeException("You are not authorized to view this request");
              }

              return roleRequestMapper.toDto(roleRequest1);


      }


    }

