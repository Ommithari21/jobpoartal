package com.example.jobpoartal.Entity;

import com.example.jobpoartal.Dto.UserRequestDto;
import com.example.jobpoartal.Repositories.UserRepository;
import com.example.jobpoartal.Service.Userservice;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Optional;

@Component
public class Superadmin implements CommandLineRunner {

    @Autowired
    private Userservice Userservice;

    @Autowired
    private UserRepository userRepository;



    @Override
    public void run(String... args) throws Exception {
        String username="SuperAdmin";
        String Password="9890033583";
        String email="SuperAdmin9983@gmail.com";


        Optional<Users> existing = Userservice.findByName(username);
        if (existing.isEmpty()) {
            Userservice.createSuperAdmin(username, email, Password);
        }

    }
}
