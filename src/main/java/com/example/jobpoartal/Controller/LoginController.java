package com.example.jobpoartal.Controller;

import com.example.jobpoartal.Dto.UserRequestDto;
import com.example.jobpoartal.Dto.UserResponseDto;
import com.example.jobpoartal.Service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/newUser")

public class LoginController {

        @Autowired
        private Userservice userservice;

        @PostMapping("/Register")
        public ResponseEntity<UserResponseDto> create(@RequestBody UserRequestDto user){
            return ResponseEntity.ok(userservice.registeruser(user));
        }

        @PostMapping("/login")
        public ResponseEntity<String>login(@RequestBody UserRequestDto users){
            return ResponseEntity.ok(userservice.login(users));
        }
}
