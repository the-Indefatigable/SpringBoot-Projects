package com.alam.CompanyMicroservice.company.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "REVIEWMICROSERVICE" ,url = "${review-service.url}")
public interface ReviewClient
{
    @GetMapping("/averageRating")
    Double getAverageRating(@RequestParam ("companyId") Long companyId);
}
