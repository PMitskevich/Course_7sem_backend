package com.mitskevich.course_7sem.service.impl;

import com.mitskevich.course_7sem.exception.DeleteEntityException;
import com.mitskevich.course_7sem.exception.ResourceNotFoundException;
import com.mitskevich.course_7sem.exception.detail.ErrorInfo;
import com.mitskevich.course_7sem.model.Doctor;
import com.mitskevich.course_7sem.model.Specialization;
import com.mitskevich.course_7sem.repository.DoctorRepository;
import com.mitskevich.course_7sem.service.interfaces.DoctorService;
import com.mitskevich.course_7sem.service.interfaces.ScheduleDayService;
import com.mitskevich.course_7sem.service.interfaces.SpecializationService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final SpecializationService specializationService;
    private final ScheduleDayService scheduleDayService;
    private final MessageSource messageSource;

    public DoctorServiceImpl(DoctorRepository doctorRepository, SpecializationService specializationService, ScheduleDayService scheduleDayService, MessageSource messageSource) {
        this.doctorRepository = doctorRepository;
        this.specializationService = specializationService;
        this.scheduleDayService = scheduleDayService;
        this.messageSource = messageSource;
    }

    @Override
    public Doctor findById(UUID id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                        messageSource.getMessage("message.ResourceNotFound",
                                new Object[]{id, messageSource.getMessage("entity.Doctor", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale())));
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        setSpecializations(doctor, doctor.getSpecializations());
        Doctor createdDoctor = doctorRepository.save(doctor);
        createdDoctor.setScheduleDays(scheduleDayService.createSchedule(createdDoctor));
        return createdDoctor;
    }

    @Override
    public Doctor updateDoctor(Doctor doctor, UUID id) {
        return doctorRepository.findById(id).map(foundDoctor -> {
            foundDoctor.setFirstName(doctor.getFirstName());
            foundDoctor.setLastName(doctor.getLastName());
            foundDoctor.setPatronymic(doctor.getPatronymic());
            foundDoctor.setExperience(doctor.getExperience());
            foundDoctor.setAddress(doctor.getAddress());
            foundDoctor.setPhone(doctor.getPhone());
            if (foundDoctor.getAppointments() != null && doctor.getAppointments() != null) {
                foundDoctor.getAppointments().clear();
                foundDoctor.setAppointments(doctor.getAppointments());
            }
            scheduleDayService.updateSchedule(foundDoctor);
            // Для создания записи в промежуточной таблице specialization_doctor (Т.к отношения М:М)
            setSpecializations(foundDoctor, doctor.getSpecializations());
            return doctorRepository.save(foundDoctor);
        }).orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                messageSource.getMessage("message.ResourceNotFound",
                        new Object[]{id,
                                messageSource.getMessage("criterion.id", null, LocaleContextHolder.getLocale()),
                                messageSource.getMessage("entity.Doctor", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale())));
    }

    @Override
    public void deleteDoctor(UUID id) {
        Doctor doctor = findById(id);
        for (Specialization specialization : doctor.getSpecializations()) {
            List<Doctor> doctors = specialization.getDoctors();
            doctors.removeIf(doctor1 -> doctor1.getId().equals(doctor.getId()));
        }
        try {
            doctorRepository.deleteById(id);
        } catch (RuntimeException exception) {
            Object[] args = new Object[]{id, messageSource.getMessage("entity.Doctor", null, LocaleContextHolder.getLocale())};
            throw new DeleteEntityException(ErrorInfo.DELETE_ENTITY_EXCEPTION,
                    messageSource.getMessage("message.DeleteEntityError", args, LocaleContextHolder.getLocale()));
        }
    }

    private void setSpecializations(Doctor doctor, List<Specialization> newSpecializationsInDoctor) {
        if (newSpecializationsInDoctor != null) {
            List<Specialization> oldSpecializationsInDoctor = doctor.getSpecializations();
            List<Specialization> resultSpecializations;
            if (!oldSpecializationsInDoctor.equals(newSpecializationsInDoctor)) {
                //Update кейс
                resultSpecializations = getSpecializationListForUpdateCase();
            } else {
                resultSpecializations = newSpecializationsInDoctor;
            }
            for (Specialization specializationInDoctor: resultSpecializations) {
                if (!doctor.getSpecializations().equals(newSpecializationsInDoctor)) {
                    if (oldSpecializationsInDoctor.stream()
                            .anyMatch(doctorSpecialization -> specializationInDoctor.getId().equals(doctorSpecialization.getId()))) {
                        continue;
                    }
                }
                Specialization specInDB = specializationService.findById(specializationInDoctor.getId());
                List<Doctor> doctorsInSpecialization = specInDB.getDoctors();
                if (doctorsInSpecialization == null) {
                    doctorsInSpecialization = new ArrayList<>();
                    specInDB.setDoctors(doctorsInSpecialization);
                }
                doctorsInSpecialization.add(doctor);
            }
            doctor.setSpecializations(newSpecializationsInDoctor);
        }
    }

    private List<Specialization> getSpecializationListForUpdateCase(List<Specialization> oldSpecializationsInDoctor,
                                                                    List<Specialization> newSpecializationsInDoctor) {
        List<Specialization> resultSpecializations;
        
        return resultSpecializations;
    }
}