package com.alam.jobapplication.reviews.impl;

import com.alam.jobapplication.company.Company;
import com.alam.jobapplication.company.CompanyRepository;
import com.alam.jobapplication.company.CompanyService;
import com.alam.jobapplication.reviews.Review;
import com.alam.jobapplication.reviews.ReviewRepository;
import com.alam.jobapplication.reviews.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }
    @Override
    public boolean updateReview(long companyId, Long reviewId, Review updatedReview) {
        if (companyService.getCompanyById(companyId) != null) {
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(long companyId, long reviewId) {
       if (companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId)) {
           Review review = reviewRepository.findById(reviewId).orElse(null);
           Company company = companyService.getCompanyById(companyId);
           company.getReviews().remove(review);
           review.setCompany(null);
           companyService.updateCompanyById(companyId, company);
           reviewRepository.deleteById(reviewId);
           return true;
       }
       return false;
//        if (reviewRepository.existsById(reviewId)) {
//            reviewRepository.deleteById(reviewId);
//            return true;
//        }
//        return false;
    }

    @Override
    public List<Review> getReviews(long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }    else {
            return false;
        }

    }

    @Override
    public Review getReviewById(long companyId, long reviewId) {
        List<Review> reviews = getReviews(companyId);
        for (Review review : reviews) {
            if (review.getId() == reviewId) {
                return review;
            }
        }
        return null;
    }
}
