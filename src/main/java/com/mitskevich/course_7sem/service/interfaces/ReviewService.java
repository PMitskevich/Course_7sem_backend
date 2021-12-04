package com.mitskevich.course_7sem.service.interfaces;

import com.mitskevich.course_7sem.model.Review;

import java.util.List;
import java.util.UUID;

public interface ReviewService {
    Review findById(UUID reviewId);
    Review saveReview(Review review);
    Review updateReview(Review review, UUID reviewId);
    void deleteReview(UUID reviewId);
    List<Review> findByUserId(UUID userId);
    List<Review> findAll();
}
