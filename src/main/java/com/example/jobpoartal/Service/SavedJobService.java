package com.example.jobpoartal.Service;


import com.example.jobpoartal.Dto.SavedJobRequestDto;
import com.example.jobpoartal.Dto.SavedJobResponseDto;
import com.example.jobpoartal.Entity.SavedJobs;
import com.example.jobpoartal.Entity.Users;
import com.example.jobpoartal.Mapper.SavedJobMapper;
import com.example.jobpoartal.Repositories.SavedJobRepository;
import com.example.jobpoartal.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SavedJobService {

    private final UserRepository userRepository;
    private final SavedJobRepository saveRepo;
private  final SavedJobMapper savedJobMapper;

    @Autowired
    public SavedJobService(UserRepository userRepository, SavedJobRepository saveRepo, SavedJobMapper savedJobMapper) {
        this.userRepository = userRepository;
        this.saveRepo = saveRepo;
        this.savedJobMapper = savedJobMapper;
    }



public SavedJobResponseDto SaveJob(Long id, SavedJobRequestDto  Request){
        Users  user=userRepository.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));

SavedJobs job=savedJobMapper.toentity(Request);

        job.setUsers(user);
    job.setSavedAt(LocalDateTime.now());
    SavedJobs savedJobs=saveRepo.save(job);

    return savedJobMapper.todto(savedJobs);

}


public List<SavedJobResponseDto>getJobs(Long id){
        Users user=userRepository.findById(id).orElseThrow(()->new RuntimeException(" user is not found"));
      List<SavedJobs> job=  saveRepo.findByUsers(user);
        return job.stream().map(savedJobMapper::todto)
                .collect(Collectors.toList());

}

public void removeSaveJob(Long id){
        saveRepo.deleteById(id);
}


}
