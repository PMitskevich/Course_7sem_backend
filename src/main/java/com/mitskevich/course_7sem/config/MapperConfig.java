package com.mitskevich.course_7sem.config;

import com.mitskevich.course_7sem.service.mapper.AppointmentMapper;
//import com.mitskevich.course_7sem.service.mapper.OwnerMapper;
import com.mitskevich.course_7sem.service.mapper.ReviewMapper;
//import com.mitskevich.course_7sem.service.mapper.UserMapper;
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
//
//    @Bean
//    public OwnerMapper ownerMapperBean() {
//        return Mappers.getMapper(OwnerMapper.class);
//    }
//
//    @Bean
//    public AppointmentMapper appointmentMapperBean() {
//        return Mappers.getMapper(AppointmentMapper.class);
//    }
}
