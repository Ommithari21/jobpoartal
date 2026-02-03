package com.example.jobpoartal.Repositories;

import com.example.jobpoartal.Entity.RoleRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRequestRepository extends JpaRepository<RoleRequest,Long > {

//Optional<RoleRequest> findById(Long id);
    RoleRequest findByUserId(Long userId);


}
