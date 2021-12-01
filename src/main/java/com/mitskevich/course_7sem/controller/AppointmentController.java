package com.mitskevich.course_7sem.controller;

import com.mitskevich.course_7sem.dto.AppointmentDTO;
import com.mitskevich.course_7sem.model.Appointment;
import com.mitskevich.course_7sem.service.interfaces.AnimalService;
import com.mitskevich.course_7sem.service.interfaces.AppointmentService;
import com.mitskevich.course_7sem.service.interfaces.DoctorService;
import com.mitskevich.course_7sem.service.interfaces.ScheduleDayService;
import com.mitskevich.course_7sem.service.interfaces.ScheduleTimeService;
import com.mitskevich.course_7sem.service.interfaces.SpecializationService;
import com.mitskevich.course_7sem.service.interfaces.UserService;
import com.mitskevich.course_7sem.service.mapper.AppointmentMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final AppointmentMapper appointmentMapper;

    public AppointmentController(AppointmentService appointmentService, AppointmentMapper appointmentMapper) {
        this.appointmentService = appointmentService;
        this.appointmentMapper = appointmentMapper;
    }

    @GetMapping("/myAppointments/{ownerId}")
    public List<AppointmentDTO> getAppointmentsByOwnerId(@PathVariable UUID ownerId) {
        return appointmentService.findByOwnerId(ownerId).stream()
                .map(appointmentMapper::convertToAppointmentDTO).collect(Collectors.toList());
    }

    @GetMapping("/{appointmentId}")
    public AppointmentDTO getAppointmentById(@PathVariable UUID appointmentId) {
        return appointmentMapper.convertToAppointmentDTO(appointmentService.findById(appointmentId));
    }

    @PostMapping("/addAppointment")
    public AppointmentDTO createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        Appointment appointment = appointmentMapper.convertToAppointment(appointmentDTO);
        return appointmentMapper.convertToAppointmentDTO(appointmentService.saveAppointment(appointment));
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<?> deleteAppointment(@PathVariable UUID appointmentId) {
        appointmentService.preDeleteOperations(appointmentId);
        appointmentService.deleteAppointment(appointmentId);
        return ResponseEntity.ok(Collections.singletonMap("response", "Приём успешно удалён"));
    }
}
