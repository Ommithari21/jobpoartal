package com.example.jobpoartal.Service;

import com.example.jobpoartal.Api.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExternalJobService {

    private static final String URL = "https://api.hirebase.org/v2/jobs/search";
    private static final String API_KEY = "hb_6ba750a0-f925-4fef-a420-0db7b6296d67";

    @Autowired
    private RestTemplate restTemplate;

    public ApiResponse SearchJob(String title, String location, int numResults) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-KEY", API_KEY);

        // ✅ Build flexible query
        String jsonBody = String.format(
                "{ \"page\": 1, \"limit\": %d, \"keywords\": [\"%s\"], \"location_types\": [\"Remote\", \"Hybrid\", \"In-Person\"] }",
                numResults, title
        );

        HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);
        ResponseEntity<ApiResponse> response =
                restTemplate.postForEntity(URL, request, ApiResponse.class);

        ApiResponse body = response.getBody();

        if (body == null || body.getJobs() == null) {
            System.out.println("No jobs returned from HireBase API");
            return new ApiResponse();
        }

        System.out.println("HireBase returned jobs: " + body.getJobs().size());

        // ✅ Flexible title & location filtering
        List<ApiResponse.Job> filteredJobs = body.getJobs().stream()
                .filter(job -> {
                    if (job.getJobTitle() == null) return false;
                    String jobTitle = job.getJobTitle().toLowerCase();
                    return jobTitle.contains(title.toLowerCase())
                            || jobTitle.contains("developer")
                            || jobTitle.contains("engineer")
                            || jobTitle.contains("analyst")
                            || jobTitle.contains("manager")
                            || jobTitle.contains("coder")
                            || jobTitle.contains("specialist")
                            || jobTitle.contains("assistant");
                })
                .filter(job -> {
                    if (job.getLocations() == null || job.getLocations().isEmpty()) return true;

                    return job.getLocations().stream().anyMatch(loc ->
                            (loc.getCity() != null && loc.getCity().toLowerCase().contains(location.toLowerCase())) ||
                                    (loc.getRegion() != null && loc.getRegion().toLowerCase().contains(location.toLowerCase())) ||
                                    (loc.getCountry() != null && loc.getCountry().toLowerCase().contains(location.toLowerCase())) ||
                                    (loc.getAddress() != null && loc.getAddress().toLowerCase().contains(location.toLowerCase()))
                    );
                })
                .limit(numResults)
                .collect(Collectors.toList());

        // ✅ Fallback #1: if nothing matched, return all jobs from API
        if (filteredJobs.isEmpty()) {
            System.out.println("No matching jobs found — returning general results");
            filteredJobs = body.getJobs().stream()
                    .limit(numResults)
                    .collect(Collectors.toList());
        }

        // ✅ Fallback #2: if API returned empty, do a second request with general keyword
        if (filteredJobs.isEmpty()) {
            String fallbackBody = String.format(
                    "{ \"page\": 1, \"limit\": %d, \"keywords\": [\"developer\"] }",
                    numResults
            );
            HttpEntity<String> fallbackRequest = new HttpEntity<>(fallbackBody, headers);
            ResponseEntity<ApiResponse> fallbackResponse =
                    restTemplate.postForEntity(URL, fallbackRequest, ApiResponse.class);

            ApiResponse fallback = fallbackResponse.getBody();
            if (fallback != null && fallback.getJobs() != null) {
                filteredJobs = fallback.getJobs().stream()
                        .limit(numResults)
                        .collect(Collectors.toList());
                System.out.println("Using fallback general jobs: " + filteredJobs.size());
            }
        }

        body.setJobs(filteredJobs);
        body.setTotalCount(filteredJobs.size());
        return body;
    }
}
