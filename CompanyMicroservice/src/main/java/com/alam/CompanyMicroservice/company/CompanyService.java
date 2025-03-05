package com.alam.CompanyMicroservice.company;

import com.alam.CompanyMicroservice.company.dto.ReviewMessage;

import java.util.List;

public interface CompanyService {
    List <Company> getCompanies();

    void addCompany(Company company);

    Company getCompanyById(long id);

    boolean deleteCompanyById(long id);

    boolean updateCompanyById(long id, Company newCompany);

    public void updateCompanyRating(ReviewMessage reviewMessage);
}
