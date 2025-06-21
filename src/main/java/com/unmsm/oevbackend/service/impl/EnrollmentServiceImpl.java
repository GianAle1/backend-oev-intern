package com.unmsm.oevbackend.service.impl;

import com.unmsm.oevbackend.dto.request.EnrollmentRequestDTO;
import com.unmsm.oevbackend.dto.request.EnrollmentUpdateRequestDTO;
import com.unmsm.oevbackend.dto.response.EnrollmentResponseDTO;
import com.unmsm.oevbackend.dto.response.UserResponseDTO;
import com.unmsm.oevbackend.exception.AppException;
import com.unmsm.oevbackend.mapper.EnrollmentMapper;
import com.unmsm.oevbackend.mapper.UserMapper;
import com.unmsm.oevbackend.model.*;
import com.unmsm.oevbackend.model.enums.Status;
import com.unmsm.oevbackend.repository.*;
import com.unmsm.oevbackend.service.interfaces.IEnrollmentService;
import com.unmsm.oevbackend.utils.NullPropertiesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements IEnrollmentService {

    private final IEnrollmentRepository enrollmentRepository;

    private final ICourseRepository courseRepository;

    private final IUserRepository userRepository;

    private final ILessonRepository lessonRepository;

    private final IUserLessonProgressRepository userLessonProgressRepository;

    private final EnrollmentMapper enrollmentMapper;

    private final UserMapper userMapper;

    @Override
    public EnrollmentResponseDTO createEnrollment(EnrollmentRequestDTO enrollmentRequestDTO) {
        Optional<Course> course = courseRepository.findById(enrollmentRequestDTO.getCourseId());
        if (course.isEmpty()) {
            throw new AppException("Course with id " + enrollmentRequestDTO.getCourseId() + " not found", HttpStatus.NOT_FOUND);
        }
        Optional<User> user = userRepository.findById(enrollmentRequestDTO.getUserId());
        if (user.isEmpty()) {
            throw new AppException("User with id " + enrollmentRequestDTO.getUserId() + " not found", HttpStatus.NOT_FOUND);
        }

        if (enrollmentRepository.existsEnrollmentByUserIdAndCourseId(enrollmentRequestDTO.getUserId(), enrollmentRequestDTO.getCourseId())) {
            throw new AppException("User with id " + enrollmentRequestDTO.getUserId() + " is already enrolled in course with id " + enrollmentRequestDTO.getCourseId(), HttpStatus.BAD_REQUEST);
        }

        Course courseEntity = course.get();
        courseEntity.setTotalStudents(courseEntity.getTotalStudents() + 1);

        Enrollment enrollmentEntity = Enrollment.builder()
                .course(courseEntity)
                .user(user.get())
                .status("ACTIVE")
                .progress(0.0)
                .enrollmentDate(LocalDateTime.now())
                .paid(false)
                .build();

        enrollmentRepository.save(enrollmentEntity);

        enrollUserInCourseLessons(course.get(), user.get());


        return enrollmentMapper.entityToResponseDTO(enrollmentEntity);
    }

    public void enrollUserInCourseLessons(Course course, User user) {
        // Obtener todas las lecciones del curso
        List<Lesson> lessons = lessonRepository.findLessonsByCourseId(course.getId());

        // Crear un registro de progreso para cada lección con estado NOT_COMPLETED
        List<UserLessonProgress> progressList = lessons.stream()
                .map(lesson -> UserLessonProgress.builder()
                        .user(user)
                        .lesson(lesson)
                        .status(Status.NOT_COMPLETED)
                        .build())
                .toList();

        userLessonProgressRepository.saveAll(progressList);
    }

    @Override
    public EnrollmentResponseDTO findEnrollmentById(Long enrollmentId) {
        Optional<Enrollment> enrollment = enrollmentRepository.findById(enrollmentId);
        if (enrollment.isEmpty()) {
            throw new AppException("Enrollment with id " + enrollmentId + " not found", HttpStatus.NOT_FOUND);
        }
        return enrollmentMapper.entityToResponseDTO(enrollment.get());
    }

    @Override
    public List<EnrollmentResponseDTO> findEnrollmentsByUserId(Long userId) {
        List<Enrollment> enrollments = enrollmentRepository.findEnrollmentsByUserId(userId);
        return enrollmentMapper.entityListToResponseDTOList(enrollments);

    }

    @Override
    public List<EnrollmentResponseDTO> findEnrollmentsByCourseId(Long courseId) {
        List<Enrollment> enrollments = enrollmentRepository.findEnrollmentsByCourseId(courseId);
        return enrollmentMapper.entityListToResponseDTOList(enrollments);
    }

    @Override
    public void deleteEnrollmentById(Long enrollmentId) {
        Optional<Enrollment> enrollment = enrollmentRepository.findById(enrollmentId);
        if (enrollment.isEmpty()) {
            throw new AppException("Enrollment with id " + enrollmentId + " not found", HttpStatus.NOT_FOUND);
        }

        Course course = enrollment.get().getCourse();
        course.setTotalStudents(course.getTotalStudents() - 1);

        enrollmentRepository.deleteById(enrollmentId);
    }

    @Override
    public List<UserResponseDTO> findEnrolledUsersByCourseId(Long courseId) {
        List<User> enrolledUsers = enrollmentRepository.findEnrolledUsersByCourseId(courseId);
        return userMapper.entityListToDTOList(enrolledUsers);
    }

    @Override
    public EnrollmentResponseDTO updateEnrollmentById(Long enrollmentId, EnrollmentUpdateRequestDTO enrollmentUpdateRequestDTO) {
        Enrollment existingEnrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new AppException("Enrollment with id " + enrollmentId + " not found", HttpStatus.NOT_FOUND));

        // Copiar solo propiedades no nulas del DTO a la entidad
        BeanUtils.copyProperties(enrollmentUpdateRequestDTO, existingEnrollment, NullPropertiesUtil.getNullPropertyNames(enrollmentUpdateRequestDTO));

        Enrollment updatedEnrollment = enrollmentRepository.save(existingEnrollment);
        return enrollmentMapper.entityToResponseDTO(updatedEnrollment);
    }
}
