package com.alam.ReviewMicroservice.reviews;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getReviews(@RequestParam Long companyId) {
        return new ResponseEntity<>(reviewService.getReviews(companyId) ,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestBody Review review , @RequestParam Long companyId) {
        boolean success = reviewService.addReview(companyId, review);
        if (success) {
            return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Review not added", HttpStatus.NOT_FOUND);
    }

    @GetMapping("{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId) {

        if (reviewService.getReviewById(reviewId) != null) {
            return new ResponseEntity<>(reviewService.getReviewById(reviewId), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> UpdateReview(@PathVariable Long reviewId, @RequestBody Review newReview) {
        boolean updated  = reviewService.updateReview(reviewId , newReview);
       if (updated)
       {
           return new ResponseEntity<>("Review updated", HttpStatus.OK);
       }
       return new ResponseEntity<>("Review not updated", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> DeleteReview( @PathVariable Long reviewId) {
        boolean deleted = reviewService.deleteReview(reviewId);
        if (deleted)
        {
            return new ResponseEntity<>("Review deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not Found", HttpStatus.NOT_FOUND);
    }

}
