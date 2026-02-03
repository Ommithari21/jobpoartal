package com.example.jobpoartal.Controller;

import com.example.jobpoartal.Dto.SavedJobRequestDto;
import com.example.jobpoartal.Dto.SavedJobResponseDto;
import com.example.jobpoartal.Entity.SavedJobs;
import com.example.jobpoartal.Service.SavedJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Api/SaveJobs")
public class SavedJobController {


    private final SavedJobService savedJobService;


    @Autowired
    public SavedJobController(SavedJobService savedJobService) {
        this.savedJobService = savedJobService;
    }


    @PostMapping("/SaveJob/{id}")
   public ResponseEntity<SavedJobResponseDto>saveJob(@PathVariable Long id, @RequestBody SavedJobRequestDto request )  {
       return ResponseEntity.ok(savedJobService.SaveJob(id,request));

   }
@GetMapping("/getJobs/{id}")
   public ResponseEntity<List<SavedJobResponseDto>>getJobs(@PathVariable Long id){
       return ResponseEntity.ok(savedJobService.getJobs(id));
   }
   @DeleteMapping("/Delete/{id}")
   public ResponseEntity<SavedJobs>DeleteSave(@PathVariable Long id){
       savedJobService.removeSaveJob(id);
       return ResponseEntity.noContent().build();
   }

}
