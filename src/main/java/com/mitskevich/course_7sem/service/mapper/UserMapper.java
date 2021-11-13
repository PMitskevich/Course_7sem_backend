package com.mitskevich.course_7sem.service.mapper;

import com.mitskevich.course_7sem.dto.UserDTO;
import com.mitskevich.course_7sem.model.User;
import com.mitskevich.course_7sem.service.mapper.helper.OwnerMapperHelper;
import com.mitskevich.course_7sem.service.mapper.helper.UserMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {UserMapperHelper.class, OwnerMapperHelper.class})
public interface UserMapper {
    @Mapping(target = "owner", qualifiedByName = "noUserOwner")
    @Mapping(target = "password", ignore = true)
    UserDTO convertToUserDTO(User user);
    User convertToUser(UserDTO userDTO);
}
