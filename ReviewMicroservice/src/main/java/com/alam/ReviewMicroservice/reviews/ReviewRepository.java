package com.alam.ReviewMicroservice.reviews;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findByCompanyId(long companyId);

    void deleteByCompanyId(long id);
}
