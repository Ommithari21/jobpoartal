package com.example.jobpoartal.Controller;

import com.example.jobpoartal.Dto.RoleResponseDto;
import com.example.jobpoartal.Entity.RoleRequest;
import com.example.jobpoartal.Security.Userdetails;
import com.example.jobpoartal.Service.RoleAssigningService;
import com.example.jobpoartal.Service.RoleRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController

@RequestMapping("/Role_assign")

public class RoleAssignController {

    @Autowired
    private RoleAssigningService roleService;

    @Autowired
    private RoleAssigningService superAdminService;



    @GetMapping("/ViewRequest")
    public ResponseEntity<List<RoleResponseDto>> viewRole( ){
        return ResponseEntity.ok(roleService.viewRequest());

    }


    @PutMapping("/assign/{id}")
    public ResponseEntity<String> assignRole(@PathVariable Long id, @AuthenticationPrincipal Userdetails principal){
        return ResponseEntity.ok(superAdminService.approveRole(id,principal));

    }


    @PutMapping("/reject/{id}")
    public ResponseEntity<String> rejectRole(@PathVariable Long id,@AuthenticationPrincipal Userdetails principal) {
        return ResponseEntity.ok(superAdminService.rejectRole(id,principal));
    }
}
