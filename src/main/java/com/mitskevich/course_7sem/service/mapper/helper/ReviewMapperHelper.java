package com.mitskevich.course_7sem.service.mapper.helper;

import com.mitskevich.course_7sem.dto.ReviewDTO;
import com.mitskevich.course_7sem.dto.UserDTO;
import com.mitskevich.course_7sem.model.Review;
import com.mitskevich.course_7sem.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface ReviewMapperHelper {
    @Named("noUserReviews")
    @Mapping(target = "user", ignore = true)
    ReviewDTO convertToReviewDTOWithoutUser(Review review);

    User convertToUser(UserDTO userDTO);
}
