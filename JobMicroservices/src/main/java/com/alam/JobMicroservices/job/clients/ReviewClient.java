package com.alam.JobMicroservices.job.clients;

import com.alam.JobMicroservices.job.external.Company;
import com.alam.JobMicroservices.job.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "REVIEWMICROSERVICE" ,
        url = "${review-service.url}")
public interface ReviewClient
{
        @GetMapping
        List<Review> getReview(@RequestParam("companyId") Long companyId);
}
