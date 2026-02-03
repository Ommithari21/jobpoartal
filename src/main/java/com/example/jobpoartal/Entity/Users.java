package com.example.jobpoartal.Entity;

import com.example.jobpoartal.Enum.ROLES;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//for all users including user,admin

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;

   private String email;

  private   String password;

   private String status;

  private LocalDateTime createdAT;

   private LocalDateTime updatedAt;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<ROLES>roles=new ArrayList<>();

    public List<ROLES> getRoles() {
        return roles;
    }

    public void setRoles(List<ROLES> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAT() {
        return createdAT;
    }

    public void setCreatedAT(LocalDateTime createdAT) {
        this.createdAT = createdAT;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
