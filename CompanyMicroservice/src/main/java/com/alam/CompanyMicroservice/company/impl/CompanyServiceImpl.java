package com.alam.CompanyMicroservice.company.impl;

import com.alam.CompanyMicroservice.company.Company;
import com.alam.CompanyMicroservice.company.CompanyRepository;
import com.alam.CompanyMicroservice.company.CompanyService;
import com.alam.CompanyMicroservice.company.clients.ReviewClient;
import com.alam.CompanyMicroservice.company.dto.ReviewMessage;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final ReviewClient reviewClient;


    public CompanyServiceImpl(CompanyRepository companyRepository, ReviewClient reviewClient){
        this.companyRepository = companyRepository;

        this.reviewClient = reviewClient;
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
    public boolean deleteCompanyById(long id) {
        if (companyRepository.existsById(id)) {
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
            companyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        Company company = getCompanyById(reviewMessage.getCompanyId());
        if (company == null) {
            System.out.println("Company not found");
            return;
        }

        double averageRating = reviewClient.getAverageRating(reviewMessage.getCompanyId());
        company.setRating(averageRating);
        companyRepository.save(company);

    }
}
