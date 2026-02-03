package com.example.jobpoartal.Security;

import com.example.jobpoartal.Entity.Users;
import com.example.jobpoartal.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    private UserRepository urepo;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Users user=urepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if(user==null){
            throw new RuntimeException("User Not Found");

        }
        return new Userdetails(user);

    }
}
