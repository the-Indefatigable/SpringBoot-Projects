package com.alam.jobapplication.reviews;

import java.util.List;

public interface ReviewService  {
    boolean updateReview(long companyId, Long reviewId, Review review);
    boolean deleteReview(long companyId,long reviewId);
    List<Review> getReviews(long companyId);
    boolean addReview(long companyId, Review review);
    Review getReviewById(long companyId,long reviewId);

}
