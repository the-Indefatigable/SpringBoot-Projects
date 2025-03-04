package com.alam.jobapplication.company.impl;

import com.alam.jobapplication.company.Company;
import com.alam.jobapplication.company.CompanyRepository;
import com.alam.jobapplication.company.CompanyService;
import com.alam.jobapplication.job.Job;
import com.alam.jobapplication.job.JobRepository;
import com.alam.jobapplication.reviews.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final JobRepository jobRepository;
    private final ReviewRepository reviewRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository, JobRepository jobRepository, ReviewRepository reviewRepository) {
        this.companyRepository = companyRepository;
        this.jobRepository = jobRepository;
        this.reviewRepository = reviewRepository;
    }
    @Override
    public List <Company> getCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public void addCompany(Company company) {
        companyRepository.save(company);

    }

    @Override
    public Company getCompanyById(long id) {
       return companyRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public boolean deleteCompanyById(long id) {
        if (companyRepository.existsById(id)) {
            jobRepository.deleteAllByCompanyId(id);
            reviewRepository.deleteByCompanyId(id);
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCompanyById(long id, Company newCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            company.setName(newCompany.getName());
            company.setDescription(newCompany.getDescription());
            company.setJobs(newCompany.getJobs());
            companyRepository.save(company);
            return true;
        }
        return false;
    }
}
