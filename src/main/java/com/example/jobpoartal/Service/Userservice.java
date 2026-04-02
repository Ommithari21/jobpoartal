package com.example.jobpoartal.Service;

import com.example.jobpoartal.Dto.UserRequestDto;
import com.example.jobpoartal.Dto.UserResponseDto;
import com.example.jobpoartal.Dto.UserUpdateRequetDto;
import com.example.jobpoartal.Dto.UserUpdateResponseDto;
import com.example.jobpoartal.Enum.ROLES;
import com.example.jobpoartal.Entity.Users;
import com.example.jobpoartal.Mapper.UpdateUserMapper;
import com.example.jobpoartal.Mapper.UserMapper;
import com.example.jobpoartal.Repositories.UserRepository;
import com.example.jobpoartal.Security.JwtService;
import com.example.jobpoartal.Security.Userdetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
public class Userservice {


    private UserRepository Urepo;


    private UserMapper userMapper;


    private AuthenticationManager authenticationManager;


    private UpdateUserMapper updateUserMapper;


    private PasswordEncoder encoder;


    private JwtService jwtService;

@Autowired
    public Userservice(UserRepository urepo, UserMapper userMapper,
                       AuthenticationManager authenticationManager,
                       UpdateUserMapper updateUserMapper,
                       PasswordEncoder encoder, JwtService jwtService) {
        Urepo = urepo;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
        this.updateUserMapper = updateUserMapper;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    //for register
    public UserResponseDto registeruser(UserRequestDto Reqdto){

        if(Urepo.findByEmail(Reqdto.getEmail()).isPresent()){
            throw new RuntimeException("User with email already exists");

        }
        if(Urepo.findByName(Reqdto.getName()).isPresent()){
            throw new RuntimeException("user with same name already exists");
        }


Users user=userMapper.toentity(Reqdto);
user.setCreatedAT(LocalDateTime.now());
user.setUpdatedAt(LocalDateTime.now());
user.setStatus("Active");
user.setPassword(encoder.encode(Reqdto.getPassword()));
user.setRoles(Collections.singletonList(ROLES.USER));
Users savedUser=Urepo.save(user);

return userMapper.todto(savedUser);
    }

    //for login
    public String login(UserRequestDto users) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        users.getEmail(),
                        users.getPassword()
                )
        );

        if (authentication.isAuthenticated()) {

            Users user = Urepo.findByEmail(users.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            return jwtService.generateToken(user);
        }

        throw new RuntimeException("Invalid login credentials");
    }




    //for update
    public UserUpdateResponseDto updateUser(UserUpdateRequetDto userdt, Userdetails principal){

        Users loggeduser=  principal.getUsers();
        var existingUsers =Urepo.findById(loggeduser.getId()).orElseThrow(()->new RuntimeException("User not found "));

existingUsers.setName(userdt.getName());
existingUsers.setPassword(encoder.encode(userdt.getPassword()));
existingUsers.setEmail(userdt.getEmail());
existingUsers.setUpdatedAt(LocalDateTime.now());
existingUsers.setPhone_no(userdt.getPhone_no());
existingUsers.setAddress(userdt.getAddress());
existingUsers.setCity(userdt.getCity());
Users updateUser=Urepo.save(existingUsers);

return  updateUserMapper.todto(updateUser);
    }


    //for delete
    public void deleteUser(Userdetails principal){
        Users data=principal.getUsers();
        Users value=Urepo.findById(data.getId()).orElseThrow(()-> new RuntimeException("User not found"));
         Urepo.deleteById(value.getId());

    }

//only for the super admin setup
    public Optional<Users> findByName(String name) {
        return Urepo.findByName(name);
    }

    public Users createSuperAdmin(String name, String email, String password) {
        Users user = new Users();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(encoder.encode(password));
        user.setRoles(Collections.singletonList(ROLES.SUPERADMIN));
        user.setCreatedAT(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setStatus("Active");
        return Urepo.save(user);
    }

}
