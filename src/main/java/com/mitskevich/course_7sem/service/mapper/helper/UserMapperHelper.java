package com.mitskevich.course_7sem.service.mapper.helper;

import com.mitskevich.course_7sem.dto.OwnerDTO;
import com.mitskevich.course_7sem.dto.ReviewDTO;
import com.mitskevich.course_7sem.dto.UserDTO;
import com.mitskevich.course_7sem.model.Owner;
import com.mitskevich.course_7sem.model.Review;
import com.mitskevich.course_7sem.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.List;

@Mapper
public interface UserMapperHelper {
    @Named("noReviewsUser")
    @Mapping(target = "reviews", ignore = true)
    UserDTO convertToUserDTOWithoutReviews(User user);

    @Named("noOwnerUser")
    @Mapping(target = "owner", ignore = true)
    UserDTO convertToUserDTOWithoutOwner(User user);

    Owner convertToOwner(OwnerDTO ownerDTO);
    Review convertReview(ReviewDTO reviewDTO);
    ReviewDTO convertReviewDTO(Review review);

    @Mapping(target = "reviews", qualifiedByName = "noUserReviews")
    List<ReviewDTO> getReviewDTOList(Collection<Review> reviews);
    List<Review> getReviewList(Collection<ReviewDTO> reviews);
}