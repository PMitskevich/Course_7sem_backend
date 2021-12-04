package com.mitskevich.course_7sem.service.impl;

import com.mitskevich.course_7sem.exception.DeleteEntityException;
import com.mitskevich.course_7sem.exception.ResourceNotFoundException;
import com.mitskevich.course_7sem.exception.detail.ErrorInfo;
import com.mitskevich.course_7sem.model.Review;
import com.mitskevich.course_7sem.model.User;
import com.mitskevich.course_7sem.repository.ReviewRepository;
import com.mitskevich.course_7sem.service.interfaces.ReviewService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MessageSource messageSource;

    public ReviewServiceImpl(ReviewRepository reviewRepository, MessageSource messageSource) {
        this.reviewRepository = reviewRepository;
        this.messageSource = messageSource;
    }

    @Override
    public Review findById(UUID reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                        messageSource.getMessage("message.ResourceNotFound",
                                new Object[]{reviewId, messageSource.getMessage("entity.Review", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale())));
    }

    @Override
    public Review saveReview(Review review) {
        User user = review.getUser();
        List<Review> reviews = user.getReviews();
        if (reviews == null) {
            reviews = new ArrayList<>();
        }
        reviews.add(review);
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Review review, UUID reviewId) {
        return reviewRepository.findById(reviewId).map(review1 -> {
            review1.setDescription(review.getDescription());
            return reviewRepository.save(review1);
        }).orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                messageSource.getMessage("message.ResourceNotFound",
                        new Object[]{reviewId,
                                messageSource.getMessage("criterion.id", null, LocaleContextHolder.getLocale()),
                                messageSource.getMessage("entity.Review", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale())));
    }

    @Override
    public void deleteReview(UUID reviewId) {
        try {
            reviewRepository.deleteById(reviewId);
        } catch (RuntimeException exception) {
            Object[] args = new Object[]{reviewId, messageSource.getMessage("entity.Review", null, LocaleContextHolder.getLocale())};
            throw new DeleteEntityException(ErrorInfo.DELETE_ENTITY_EXCEPTION,
                    messageSource.getMessage("message.DeleteEntityError", args, LocaleContextHolder.getLocale()));
        }
    }

    @Override
    public List<Review> findByUserId(UUID userId) {
        return reviewRepository.findByUserId(userId);
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }
}
