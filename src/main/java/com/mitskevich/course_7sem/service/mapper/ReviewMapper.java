package com.mitskevich.course_7sem.service.mapper;

import com.mitskevich.course_7sem.dto.ReviewDTO;
import com.mitskevich.course_7sem.dto.UserDTO;
import com.mitskevich.course_7sem.model.Review;
import com.mitskevich.course_7sem.model.User;
import com.mitskevich.course_7sem.service.mapper.helper.ReviewMapperHelper;
import com.mitskevich.course_7sem.service.mapper.helper.UserMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(uses = {ReviewMapperHelper.class, UserMapperHelper.class})
public interface ReviewMapper {
    @Mapping(target = "user", qualifiedByName = "noReviewsUser")
    ReviewDTO convertToReviewDTO(Review review);
    Review convertToReview(ReviewDTO reviewDTO);
}
