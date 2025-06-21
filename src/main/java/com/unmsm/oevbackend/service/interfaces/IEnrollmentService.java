package com.unmsm.oevbackend.service.interfaces;

import com.unmsm.oevbackend.dto.request.EnrollmentRequestDTO;
import com.unmsm.oevbackend.dto.request.EnrollmentUpdateRequestDTO;
import com.unmsm.oevbackend.dto.response.EnrollmentResponseDTO;
import com.unmsm.oevbackend.dto.response.UserResponseDTO;
import com.unmsm.oevbackend.model.Enrollment;

import java.util.List;

public interface IEnrollmentService {

    EnrollmentResponseDTO createEnrollment(EnrollmentRequestDTO enrollmentRequestDTO);

    EnrollmentResponseDTO findEnrollmentById(Long enrollmentId);

    List<EnrollmentResponseDTO> findEnrollmentsByUserId(Long userId);

    List<EnrollmentResponseDTO> findEnrollmentsByCourseId(Long courseId);

    void deleteEnrollmentById(Long enrollmentId);

    List<UserResponseDTO> findEnrolledUsersByCourseId(Long courseId);

    EnrollmentResponseDTO updateEnrollmentById(Long enrollmentId, EnrollmentUpdateRequestDTO enrollmentUpdateRequestDTO);
}
