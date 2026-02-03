package com.example.jobpoartal.Repositories;

import com.example.jobpoartal.Entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JobApplicationRepository extends JpaRepository<JobApplication,Long> {


    boolean existsByUsersIdAndJobsId(Long users_id, Long jobs_id);

    @Query("select j from JobApplication j where j.jobs.id = :job_id")
    List<JobApplication> findByJobId(Long job_id);


    Optional<JobApplication> findByUsersId(Long usersId);

    // OR (if user can apply to multiple jobs)
    List<JobApplication> findAllByUsersId(Long usersId);


}
