package com.example.jobpoartal.Controller;


import com.example.jobpoartal.Service.ExternalJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Api")
public class ApiController {

    @Autowired
    private ExternalJobService jobService;

    @GetMapping("/jobs")
    public ResponseEntity<?> job(@RequestParam String title,
                                 @RequestParam String location,
                                 @RequestParam(name = "num_results", defaultValue = "10") int numResults) {
        var result = jobService.SearchJob(title, location, numResults);

        if (result.getTotalCount() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No jobs found for title '" + title + "' in location '" + location + "'");
        }

        return ResponseEntity.ok(result);
    }

}
