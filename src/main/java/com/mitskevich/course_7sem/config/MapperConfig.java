package com.mitskevich.course_7sem.config;

import com.mitskevich.course_7sem.service.mapper.AnimalMapper;
import com.mitskevich.course_7sem.service.mapper.AppointmentMapper;
//import com.mitskevich.course_7sem.service.mapper.OwnerMapper;
import com.mitskevich.course_7sem.service.mapper.DoctorMapper;
import com.mitskevich.course_7sem.service.mapper.MedicalServiceEntityMapper;
import com.mitskevich.course_7sem.service.mapper.OwnerMapper;
import com.mitskevich.course_7sem.service.mapper.ReviewMapper;
//import com.mitskevich.course_7sem.service.mapper.UserMapper;
import com.mitskevich.course_7sem.service.mapper.ScheduleDayMapper;
import com.mitskevich.course_7sem.service.mapper.ScheduleTimeMapper;
import com.mitskevich.course_7sem.service.mapper.SpecializationMapper;
import com.mitskevich.course_7sem.service.mapper.UserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public UserMapper userMapperBean() {
        return Mappers.getMapper(UserMapper.class);
    }

    @Bean
    public ReviewMapper reviewMapperBean() {
        return Mappers.getMapper(ReviewMapper.class);
    }

    @Bean
    public OwnerMapper ownerMapperBean() {
        return Mappers.getMapper(OwnerMapper.class);
    }

    @Bean
    public AppointmentMapper appointmentMapperBean() {
        return Mappers.getMapper(AppointmentMapper.class);
    }

    @Bean
    public AnimalMapper animalMapperBean() { return Mappers.getMapper(AnimalMapper.class); }

    @Bean
    public DoctorMapper doctorMapperBean() { return Mappers.getMapper(DoctorMapper.class); }

    @Bean
    public MedicalServiceEntityMapper medicalServiceEntityMapperBean() { return Mappers.getMapper(MedicalServiceEntityMapper.class); }

    @Bean
    public ScheduleDayMapper scheduleDayMapperBean() { return Mappers.getMapper(ScheduleDayMapper.class); }

    @Bean
    public ScheduleTimeMapper scheduleTimeMapperBean() { return Mappers.getMapper(ScheduleTimeMapper.class); }

    @Bean
    public SpecializationMapper specializationMapperBean() { return Mappers.getMapper(SpecializationMapper.class); }
}
