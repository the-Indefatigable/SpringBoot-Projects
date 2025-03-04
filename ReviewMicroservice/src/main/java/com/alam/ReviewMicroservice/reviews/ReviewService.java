package com.alam.ReviewMicroservice.reviews;

import java.util.List;

public interface ReviewService  {
    boolean updateReview(Long reviewId, Review review);
    boolean deleteReview(Long reviewId);
    List<Review> getReviews(Long companyId);
    boolean addReview(Long companyId, Review review);
    Review getReviewById(Long reviewId);

}
