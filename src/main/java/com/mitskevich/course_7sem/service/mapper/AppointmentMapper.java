package com.mitskevich.course_7sem.service.mapper;

import com.mitskevich.course_7sem.dto.AnimalDTO;
import com.mitskevich.course_7sem.dto.AppointmentDTO;
import com.mitskevich.course_7sem.dto.DoctorDTO;
import com.mitskevich.course_7sem.dto.MedicalServiceEntityDTO;
import com.mitskevich.course_7sem.dto.OwnerDTO;
import com.mitskevich.course_7sem.dto.ScheduleDayDTO;
import com.mitskevich.course_7sem.dto.ScheduleTimeDTO;
import com.mitskevich.course_7sem.dto.SpecializationDTO;
import com.mitskevich.course_7sem.dto.UserDTO;
import com.mitskevich.course_7sem.model.Animal;
import com.mitskevich.course_7sem.model.Appointment;
import com.mitskevich.course_7sem.model.Doctor;
import com.mitskevich.course_7sem.model.MedicalServiceEntity;
import com.mitskevich.course_7sem.model.Owner;
import com.mitskevich.course_7sem.model.ScheduleDay;
import com.mitskevich.course_7sem.model.ScheduleTime;
import com.mitskevich.course_7sem.model.Specialization;
import com.mitskevich.course_7sem.model.User;
import com.mitskevich.course_7sem.service.mapper.helper.AnimalMapperHelper;
import com.mitskevich.course_7sem.service.mapper.helper.AppointmentMapperHelper;
import com.mitskevich.course_7sem.service.mapper.helper.DoctorMapperHelper;
import com.mitskevich.course_7sem.service.mapper.helper.OwnerMapperHelper;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

@Mapper(uses = {AnimalMapperHelper.class})
public interface AppointmentMapper {
    @Mapping(target = "dateTime", qualifiedByName = "dateTimeConversionToLocalDateTime")
    Appointment convertToAppointment(AppointmentDTO appointmentDTO);

    @Mappings({
            @Mapping(target = "doctor", qualifiedByName = "noAppointmentDoctor"),
            @Mapping(target = "owner", qualifiedByName = "noAppointmentOwner"),
            @Mapping(target = "animal", qualifiedByName = "noAppointmentAnimal"),
            @Mapping(target = "dateTime", qualifiedByName = "dateTimeConversionToString")
    })
    AppointmentDTO convertToAppointmentDTO(Appointment appointment);

    @Named("dateTimeConversionToLocalDateTime")
    default LocalDateTime convertLocalDateTime(String localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(localDateTime, formatter);
    }

    @Named("dateTimeConversionToString")
    default String convertToString(LocalDateTime localDateTime) {
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(localDateTime.toLocalDate()));
        LocalTime localTime = localDateTime.toLocalTime();
        stringBuilder.append(" ").append(localTime.getHour());
        int minutes = localTime.getMinute();
        stringBuilder.append(":").append(minutes == 0 ? "00" : minutes);
        return stringBuilder.toString();
    }

//    Конвертация доктора
    @Named("noAppointmentDoctor")
    @Mappings({
            @Mapping(target = "scheduleDays", qualifiedByName = "scheduleDayDTO"),
            @Mapping(target = "specializations", qualifiedByName = "specializationsWithoutDoctor"),
            @Mapping(target = "appointments", ignore = true)
    })
    DoctorDTO convertToDoctorDTOWithoutAppointment(Doctor doctor);

    @Named("scheduleDayDTO")
    @Mappings({
            @Mapping(target = "doctor", qualifiedByName = "noScheduleDayDoctor"),
            @Mapping(target = "scheduleTimes", qualifiedByName = "scheduleTimeDTO")
    })
    ScheduleDayDTO convertToScheduleDayDTO(ScheduleDay scheduleDay);

    @Named("scheduleTimeDTO")
    @Mapping(target = "scheduleDay", qualifiedByName = "noScheduleTimeInScheduleDay")
    ScheduleTimeDTO convertToScheduleTimeDTO(ScheduleTime scheduleTime);
    ScheduleTime convertToScheduleTime(ScheduleTimeDTO scheduleTime);

    @Named("noScheduleTimeInScheduleDay")
    @Mappings({
            @Mapping(target = "scheduleTimes", ignore = true),
            @Mapping(target = "doctor", ignore = true)
    })
    ScheduleDayDTO convertToScheduleDayDTOWithoutScheduleTimes(ScheduleDay scheduleDay);

    @Named("noScheduleDayDoctor")
    @Mappings({
            @Mapping(target = "scheduleDays", ignore = true),
            @Mapping(target = "specializations", ignore = true),
            @Mapping(target = "appointments", ignore = true)
    })
    DoctorDTO convertToDoctorDTOWithoutScheduleDays(Doctor doctor);

    @Named("specializationsWithoutDoctor")
    @IterableMapping(qualifiedByName = "noDoctorSpecializations")
    List<SpecializationDTO> getSpecializationDTOListWithoutDoctor(Collection<Specialization> specializations);
    List<Specialization> getSpecializationList(Collection<SpecializationDTO> specializations);

    @Named("noDoctorSpecializations")
    @Mappings({
            @Mapping(target = "doctors", ignore = true),
            @Mapping(target = "medicalServiceEntities", qualifiedByName = "medServiceEntitiesWithoutSpecialization")
    })
    SpecializationDTO convertToSpecializationDTOWithoutDoctors(Specialization specialization);

    @Named("medServiceEntitiesWithoutSpecialization")
    @IterableMapping(qualifiedByName = "medServiceEntityWithoutSpecialization")
    List<MedicalServiceEntityDTO> getMedicalServiceEntitiesDTOWithoutSpecialization(Collection<MedicalServiceEntity> medicalServiceEntities);

    @Named("medServiceEntityWithoutSpecialization")
    @Mapping(target = "specialization", ignore = true)
    MedicalServiceEntityDTO getMedicalServiceEntitiesDTOWithoutSpecialization(MedicalServiceEntity medicalServiceEntity);

//    Конвертация владельца животного
    @Named("noAppointmentOwner")
    @Mappings({
            @Mapping(target = "appointments", ignore = true),
            @Mapping(target = "animals", qualifiedByName = "noOwnerAnimals"),
            @Mapping(target = "user", qualifiedByName = "noOwnerUser")
    })
    OwnerDTO convertToOwnerDTOWithoutAppointments(Owner owner);

    @Named("noOwnerAnimals")
    @IterableMapping(qualifiedByName = "noOwnerAnimal")
    List<AnimalDTO> getAnimalDTOList(Collection<Animal> animals);
    List<Animal> getAnimalList(Collection<AnimalDTO> animals);

    @Named("noOwnerAnimal")
    @Mappings({
            @Mapping(target = "owner", ignore = true),
            @Mapping(target = "appointments", ignore = true)
    })
    AnimalDTO convertToAnimalDTOWithoutOwner(Animal animal);

    @Named("noOwnerUser")
    @Mapping(target = "owner", ignore = true)
    UserDTO convertToUserDTOWithoutOwner(User user);
}
