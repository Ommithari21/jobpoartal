package com.example.jobpoartal.Controller;

import com.example.jobpoartal.Dto.UserRequestDto;
import com.example.jobpoartal.Dto.UserResponseDto;
import com.example.jobpoartal.Dto.UserUpdateRequetDto;
import com.example.jobpoartal.Dto.UserUpdateResponseDto;
import com.example.jobpoartal.Entity.Users;
import com.example.jobpoartal.Security.Userdetails;
import com.example.jobpoartal.Service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class Usercontroller {

    @Autowired
    private Userservice userservice;



    @DeleteMapping("/log_out")
    public ResponseEntity<Void> Delete(@AuthenticationPrincipal Userdetails principal){
        userservice.deleteUser(principal);
        return ResponseEntity.noContent().build();

    }

@PutMapping("/update")
    public ResponseEntity<UserUpdateResponseDto>update(@RequestBody UserUpdateRequetDto udto, @AuthenticationPrincipal Userdetails principal){
        return ResponseEntity.ok(userservice.updateUser(udto,principal));

    }

}
