package com.mitskevich.course_7sem.controller;

import com.mitskevich.course_7sem.dto.ReviewDTO;
import com.mitskevich.course_7sem.model.Review;
import com.mitskevich.course_7sem.service.interfaces.ReviewService;
import com.mitskevich.course_7sem.service.mapper.ReviewMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    public ReviewController(ReviewService reviewService, ReviewMapper reviewMapper) {
        this.reviewService = reviewService;
        this.reviewMapper = reviewMapper;
    }

    @GetMapping("/all")
    public List<ReviewDTO> getAllReviews() {
        return reviewService.findAll().stream().map(reviewMapper::convertToReviewDTO).collect(Collectors.toList());
    }

    @GetMapping("/user/{userId}")
    public List<ReviewDTO> getReviewsByUserId(@PathVariable UUID userId) {
        return reviewService.findByUserId(userId).stream().map(reviewMapper::convertToReviewDTO).collect(Collectors.toList());
    }

    @PostMapping("/addReview")
    public ReviewDTO createReview(@RequestBody ReviewDTO reviewDTO) {
        Review review = reviewMapper.convertToReview(reviewDTO);
        return reviewMapper.convertToReviewDTO(reviewService.saveReview(review));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable UUID reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok(Collections.singletonMap("response", "Отзыв успешно удалён"));
    }
}
