package com.example.jobpoartal.Repositories;

import com.example.jobpoartal.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job,Long> {

    List<Job> findByUserId(Long id);
    List<Job> findByLocationAndType(String Location, String type);
}
