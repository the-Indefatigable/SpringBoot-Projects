package com.alam.CompanyMicroservice.company.impl;

import com.alam.CompanyMicroservice.company.Company;
import com.alam.CompanyMicroservice.company.CompanyRepository;
import com.alam.CompanyMicroservice.company.CompanyService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;


    public CompanyServiceImpl(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;

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
}
