package com.mitskevich.course_7sem.controller;

import com.mitskevich.course_7sem.dto.DoctorDTO;
import com.mitskevich.course_7sem.model.Doctor;
import com.mitskevich.course_7sem.service.interfaces.DoctorService;
import com.mitskevich.course_7sem.service.mapper.DoctorMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;

    public DoctorController(DoctorService doctorService, DoctorMapper doctorMapper) {
        this.doctorService = doctorService;
        this.doctorMapper = doctorMapper;
    }

    @GetMapping("/all")
    public List<DoctorDTO> getAllDoctors() {
        return doctorService.findAll().stream().map(doctorMapper::convertToDoctorDTO).collect(Collectors.toList());
    }

    @GetMapping("/{doctorId}")
    public DoctorDTO getDoctorById(@PathVariable UUID doctorId) {
        return doctorMapper.convertToDoctorDTO(doctorService.findById(doctorId));
    }

    @PostMapping("/addDoctor")
    public DoctorDTO createDoctor(@RequestBody DoctorDTO doctorDTO) {
        Doctor doctor = doctorMapper.convertToDoctor(doctorDTO);
        return doctorMapper.convertToDoctorDTO(doctorService.saveDoctor(doctor));
    }

    @PutMapping("/{doctorId}")
    public DoctorDTO updateDoctor(@PathVariable UUID doctorId, @RequestBody DoctorDTO doctorDTO) {
        Doctor doctor = doctorMapper.convertToDoctor(doctorDTO);
        return doctorMapper.convertToDoctorDTO(doctorService.updateDoctor(doctor, doctorId));
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<?> deleteDoctor(@PathVariable UUID doctorId) {
        doctorService.deleteDoctor(doctorId);
        return ResponseEntity.ok(Collections.singletonMap("response", "Доктор успешно удалён"));
    }
}
