package com.mitskevich.course_7sem.service.mapper.helper;

import com.mitskevich.course_7sem.dto.AppointmentDTO;
import com.mitskevich.course_7sem.dto.OwnerDTO;
import com.mitskevich.course_7sem.dto.ReviewDTO;
import com.mitskevich.course_7sem.dto.UserDTO;
import com.mitskevich.course_7sem.model.Appointment;
import com.mitskevich.course_7sem.model.Owner;
import com.mitskevich.course_7sem.model.Review;
import com.mitskevich.course_7sem.model.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

@Mapper
public interface ReviewMapperHelper {
    @Named("noUserReviews")
    @Mapping(target = "user", ignore = true)
    ReviewDTO convertToReviewDTOWithoutUser(Review review);

    @Mapping(target = "owner", qualifiedByName = "ownerWithConvertedLocalDate")
    User convertToUser(UserDTO userDTO);

    @Named("ownerWithConvertedLocalDate")
    @Mappings({
            @Mapping(target = "appointments", qualifiedByName = "appointmentsWithConvertedDates"),
            @Mapping(target = "animals", ignore = true)
    })
    Owner convertToOwnerDTOWithConvertedLocalDate(OwnerDTO ownerDTO);

    @Named("appointmentsWithConvertedDates")
    @IterableMapping(qualifiedByName = "appointmentWithConvertedDates")
    List<Appointment> convertAppointmentDTOListWithLocalDate(Collection<AppointmentDTO> appointmentDTOS);

    @Named("appointmentWithConvertedDates")
    @Mapping(target = "dateTime", qualifiedByName = "dateTimeConversionToLocalDateTime")
    Appointment convertToAppointmentWithLocalDate(AppointmentDTO appointmentDTO);

    @Named("dateTimeConversionToLocalDateTime")
    default LocalDateTime convertLocalDateTime(String localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(localDateTime, formatter);
    }
}
