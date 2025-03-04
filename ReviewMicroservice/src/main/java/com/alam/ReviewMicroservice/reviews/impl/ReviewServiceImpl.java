package com.alam.ReviewMicroservice.reviews.impl;

import com.alam.ReviewMicroservice.reviews.Review;
import com.alam.ReviewMicroservice.reviews.ReviewRepository;
import com.alam.ReviewMicroservice.reviews.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
    @Override
    public boolean updateReview(Long reviewId, Review updatedReview) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null) {
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setCompanyId(updatedReview.getCompanyId());
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
       if (review != null) {
//           reviewRepository.deleteById(reviewId);
           //or
           reviewRepository.delete(review);
           return true;

       }
       return false;

    }

    @Override
    public List<Review> getReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {

        if (companyId != null && review != null) {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }    else {
            return false;
        }

    }

    @Override
    public Review getReviewById( Long reviewId) {
     return reviewRepository.findById(reviewId).orElse(null);
    }
}
