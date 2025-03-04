package com.alam.jobapplication.reviews;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Repeatable;
import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private final ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getReviews(Review review, @PathVariable Long companyId) {
        return new ResponseEntity<>(reviewService.getReviews(companyId) ,HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@RequestBody Review review , @PathVariable Long companyId) {
        boolean success = reviewService.addReview(companyId, review);
        if (success) {
            return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Review not added", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId , @PathVariable Long companyId) {

        if (reviewService.getReviewById(companyId , reviewId) != null) {
            return new ResponseEntity<>(reviewService.getReviewById(companyId ,reviewId), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> UpdateReview(@PathVariable Long companyId,@PathVariable Long reviewId, @RequestBody Review newReview) {
        boolean updated  = reviewService.updateReview(companyId,reviewId , newReview);
       if (updated)
       {
           return new ResponseEntity<>("Review updated", HttpStatus.OK);
       }
       return new ResponseEntity<>("Review not updated", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> DeleteReview(@PathVariable Long companyId , @PathVariable Long reviewId) {
        boolean deleted = reviewService.deleteReview(companyId , reviewId);
        if (deleted)
        {
            return new ResponseEntity<>("Review deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not Found", HttpStatus.NOT_FOUND);
    }

}
