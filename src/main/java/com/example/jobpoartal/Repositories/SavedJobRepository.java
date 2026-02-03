package com.example.jobpoartal.Repositories;

import com.example.jobpoartal.Entity.SavedJobs;
import com.example.jobpoartal.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedJobRepository extends JpaRepository<SavedJobs,Long> {

    List<SavedJobs> findByUsers(Users users);

}
