package com.mitskevich.course_7sem.controller;

import com.mitskevich.course_7sem.dto.SpecializationDTO;
import com.mitskevich.course_7sem.model.Specialization;
import com.mitskevich.course_7sem.service.interfaces.SpecializationService;
import com.mitskevich.course_7sem.service.mapper.SpecializationMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/specialization")
public class SpecializationController {

    private final SpecializationMapper specializationMapper;
    private final SpecializationService specializationService;

    public SpecializationController(SpecializationMapper specializationMapper, SpecializationService specializationService) {
        this.specializationMapper = specializationMapper;
        this.specializationService = specializationService;
    }

    @GetMapping("/all")
    public List<SpecializationDTO> getAllSpecializations() {
        return specializationService.findAll()
                .stream().map(specializationMapper::convertToSpecializationDTO).collect(Collectors.toList());
    }

    @GetMapping("/{specializationId}")
    public SpecializationDTO getSpecializationById(@PathVariable UUID specializationId) {
        return specializationMapper.convertToSpecializationDTO(specializationService.findById(specializationId));
    }

    @PutMapping("/{specializationId}")
    public ResponseEntity<?> updateSpecialization(@PathVariable UUID specializationId, @RequestBody SpecializationDTO specializationDTO) {
        Specialization specialization = specializationMapper.convertToSpecialization(specializationDTO);
        if ((specialization = specializationService.updateSpecialization(specializationId, specialization)) != null) {
            return ResponseEntity.ok(specializationMapper.convertToSpecializationDTO(specialization));
        } else {
            return ResponseEntity.badRequest().body("??????-???? ?????????? ???? ?????? ?? ?????????????????????? ??????????????????????????...");
        }
    }

    @DeleteMapping("/{specializationId}")
    public ResponseEntity<?> deleteSpecialization(@PathVariable UUID specializationId) {
        specializationService.deleteById(specializationId);
        return ResponseEntity.ok(Collections.singletonMap("response", "?????????????????????????? ?????????????? ??????????????!"));
    }

    @PostMapping("/addSpecialization")
    public ResponseEntity<?> addNewSpecialization(@RequestBody SpecializationDTO specializationDTO) {
        Specialization specialization = specializationMapper.convertToSpecialization(specializationDTO);
        if ((specialization = specializationService.saveSpecialization(specialization)) != null) {
            return ResponseEntity.ok(specializationMapper.convertToSpecializationDTO(specialization));
        } else {
            return ResponseEntity.badRequest().body("??????-???? ?????????? ???? ?????? ?? ?????????????????? ?????????? ??????????????????????????...");
        }
    }
}
