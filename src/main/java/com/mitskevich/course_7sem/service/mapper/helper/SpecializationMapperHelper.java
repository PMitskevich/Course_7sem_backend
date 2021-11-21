package com.mitskevich.course_7sem.service.mapper.helper;

import com.mitskevich.course_7sem.dto.DoctorDTO;
import com.mitskevich.course_7sem.dto.MedicalServiceEntityDTO;
import com.mitskevich.course_7sem.dto.SpecializationDTO;
import com.mitskevich.course_7sem.model.Doctor;
import com.mitskevich.course_7sem.model.MedicalServiceEntity;
import com.mitskevich.course_7sem.model.Specialization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.List;

@Mapper(uses = {DoctorMapperHelper.class})
public interface SpecializationMapperHelper {
    @Named("noDoctorSpecialization")
    @Mapping(target = "doctors", ignore = true)
    SpecializationDTO convertToSpecializationDTOWithoutDoctors(Specialization specialization);

    @Named("noMedicalServiceEntityInSpecialization")
    @Mapping(target = "medicalServiceEntities", ignore = true)
    SpecializationDTO convertToSpecializationDTOWithoutMedicalServiceEntitys(Specialization specialization);

    @Mapping(target = "doctors", qualifiedByName = "noSpecializationsInDoctor")
    List<DoctorDTO> getDoctorDTOList(Collection<Doctor> doctors);
    List<Doctor> getDoctoriList(Collection<DoctorDTO> doctors);

//    @Mapping(target = "medicalServiceEntities", qualifiedByName = "noSpecializationInMedicalServiceEntity")
//    List<MedicalServiceEntityDTO> getMedicalServiceEntityDTOList(Collection<MedicalServiceEntity> medicalServiceEntities);
    List<MedicalServiceEntity> getMedicalServiceEntityList(Collection<MedicalServiceEntityDTO> medicalServiceEntitys);
}
