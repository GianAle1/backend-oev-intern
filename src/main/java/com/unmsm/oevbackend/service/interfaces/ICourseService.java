package com.unmsm.oevbackend.service.interfaces;

import com.unmsm.oevbackend.dto.request.CourseRequestDTO;
import com.unmsm.oevbackend.dto.request.UpdateCourseRequestDTO;
import com.unmsm.oevbackend.dto.response.CourseResponseDTO;
import com.unmsm.oevbackend.dto.response.LessonResponseDTO;
import com.unmsm.oevbackend.model.Course;
import com.unmsm.oevbackend.model.Lesson;

import java.util.List;

public interface ICourseService {

    List<CourseResponseDTO> findAllCourses();

    List<CourseResponseDTO> findAllCoursesByUserId(Long userId);

    CourseResponseDTO findCourseById(Long id);

    CourseResponseDTO updateCourseById(Long id, UpdateCourseRequestDTO updateCourseRequestDTO);

    void deleteCourseById(Long id);

    CourseResponseDTO createCourse(Long userId, CourseRequestDTO courseRequestDTO);

}
