package com.unmsm.oevbackend.controller;

import com.unmsm.oevbackend.dto.request.EnrollmentRequestDTO;
import com.unmsm.oevbackend.dto.request.EnrollmentUpdateRequestDTO;
import com.unmsm.oevbackend.dto.response.EnrollmentResponseDTO;
import com.unmsm.oevbackend.dto.response.UserResponseDTO;
import com.unmsm.oevbackend.service.interfaces.IEnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollment")
@RequiredArgsConstructor
public class EnrollmentRestController {

    private final IEnrollmentService enrollmentService;

    @PostMapping("/create")
    public ResponseEntity<EnrollmentResponseDTO> createEnrollment(@Valid @RequestBody EnrollmentRequestDTO enrollmentRequestDTO) {
        EnrollmentResponseDTO response = enrollmentService.createEnrollment(enrollmentRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/findEnrollment/{id}")
    public ResponseEntity<EnrollmentResponseDTO> findEnrollmentById(@PathVariable Long id) {
        EnrollmentResponseDTO response = enrollmentService.findEnrollmentById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/findAllByUserId/{userId}")
    public ResponseEntity<List<EnrollmentResponseDTO>> findEnrollmentsByUserId(@PathVariable Long userId) {
        List<EnrollmentResponseDTO> response = enrollmentService.findEnrollmentsByUserId(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/findAllByCourseId/{courseId}")
    public ResponseEntity<List<EnrollmentResponseDTO>> findEnrollmentsByCourseId(@PathVariable Long courseId) {
        List<EnrollmentResponseDTO> response = enrollmentService.findEnrollmentsByCourseId(courseId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/findEnrolledUsersByCourseId/{courseId}")
    public ResponseEntity<List<UserResponseDTO>> findEnrolledUsersByCourseId(@PathVariable Long courseId) {
        List<UserResponseDTO> response = enrollmentService.findEnrolledUsersByCourseId(courseId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEnrollmentById(@PathVariable Long id) {
        enrollmentService.deleteEnrollmentById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<EnrollmentResponseDTO> updateEnrollmentById(@PathVariable Long id, @Valid @RequestBody EnrollmentUpdateRequestDTO enrollmentUpdateRequestDTO) {
        EnrollmentResponseDTO response = enrollmentService.updateEnrollmentById(id, enrollmentUpdateRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
