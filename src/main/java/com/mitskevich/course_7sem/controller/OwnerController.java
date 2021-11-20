package com.mitskevich.course_7sem.controller;

import com.mitskevich.course_7sem.dto.OwnerDTO;
import com.mitskevich.course_7sem.model.Owner;
import com.mitskevich.course_7sem.service.interfaces.OwnerService;
import com.mitskevich.course_7sem.service.mapper.OwnerMapper;
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

import java.util.UUID;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerService ownerService;
    private final OwnerMapper ownerMapper;

    public OwnerController(OwnerService ownerService, OwnerMapper ownerMapper) {
        this.ownerService = ownerService;
        this.ownerMapper = ownerMapper;
    }

    @GetMapping("/{ownerId}")
    public OwnerDTO getOwnerById(@PathVariable UUID ownerId) {
        return ownerMapper.convertToOwnerDTO(ownerService.findById(ownerId));
    }

    @PostMapping("/addOwner")
    public OwnerDTO saveOwner(@RequestBody OwnerDTO ownerDTO) {
        Owner owner = ownerMapper.convertToOwner(ownerDTO);
        return ownerMapper.convertToOwnerDTO(ownerService.saveOwner(owner));
    }

    @PutMapping("/{ownerId}")
    public OwnerDTO updateOwner(@PathVariable UUID ownerId, @RequestBody OwnerDTO ownerDTO) {
        Owner owner = ownerMapper.convertToOwner(ownerDTO);
        return ownerMapper.convertToOwnerDTO(ownerService.updateOwner(owner, ownerId));
    }

    @DeleteMapping("/{ownerId}")
    public ResponseEntity<?> deleteOwner(@PathVariable UUID ownerId) {
        ownerService.deleteOwner(ownerId);
        return ResponseEntity.ok("Владелец успешно удалён");
    }
}
