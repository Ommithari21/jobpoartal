package com.example.jobpoartal.Controller;


import com.example.jobpoartal.Dto.RoleRequestDto;
import com.example.jobpoartal.Dto.RoleResponseDto;
import com.example.jobpoartal.Security.Userdetails;
import com.example.jobpoartal.Service.RoleRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/RoleRequest")
public class RoleRequestController {

    @Autowired
    private RoleRequestService roleService;


    @PostMapping("/SendRequest")
  public ResponseEntity<RoleResponseDto>SendRole(@RequestBody RoleRequestDto request, @AuthenticationPrincipal Userdetails principal){
      return ResponseEntity.ok(roleService.SendRequest(request,principal));
  }


  @GetMapping("/viewRequest_user")
  public ResponseEntity<RoleResponseDto>viewRequest(@AuthenticationPrincipal Userdetails principal){
        return ResponseEntity.ok(roleService.roleStatus(principal));
  }


}
