package com.mitskevich.course_7sem.service.mapper;

import com.mitskevich.course_7sem.dto.OwnerDTO;
import com.mitskevich.course_7sem.dto.ReviewDTO;
import com.mitskevich.course_7sem.dto.UserDTO;
import com.mitskevich.course_7sem.model.Owner;
import com.mitskevich.course_7sem.model.Review;
import com.mitskevich.course_7sem.model.User;
import com.mitskevich.course_7sem.service.mapper.helper.UserMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.List;

@Mapper(uses = {UserMapperHelper.class})
public interface UserMapper {
    User convertToUser(UserDTO userDTO);

    @Mapping(target = "owner", qualifiedByName = "noUserOwner")
    UserDTO convertToUserDTO(User user);
}
