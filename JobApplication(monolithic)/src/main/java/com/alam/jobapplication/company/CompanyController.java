package com.alam.jobapplication.company;

import com.alam.jobapplication.job.Job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }



    @GetMapping("/companies")
    public List<Company> getCompanies()
    {
       return companyService.getCompanies();
    }

    @PostMapping("/companies")
    public String addCompany(@RequestBody Company company)
    {
        companyService.addCompany(company);
        return "Company added successfully";
    }

   @GetMapping("companies/{id}")
   public ResponseEntity<Company> getCompanyById(@PathVariable long id)
   {
       if (companyService.getCompanyById(id) != null){
           return new ResponseEntity<>(companyService.getCompanyById(id) , HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }


   @DeleteMapping("/delete/companies/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable long id)
   {
       boolean deleted = companyService.deleteCompanyById(id);
       if (deleted){
           return new ResponseEntity<>(HttpStatus.OK);
       }
       return new ResponseEntity<>("Company not found ",HttpStatus.NOT_FOUND);
   }

   @PutMapping("/companies/{id}")
    public ResponseEntity<String> updateCompanyById(@PathVariable long id, @RequestBody Company newCompany)
   {
       boolean updated = companyService.updateCompanyById(id , newCompany);
       if (updated){
           return new ResponseEntity<>(HttpStatus.OK);
       }
       return new ResponseEntity<>("Company not found",HttpStatus.NOT_FOUND);
   }










}
