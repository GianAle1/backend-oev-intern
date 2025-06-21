package com.unmsm.oevbackend.service.interfaces;

import com.unmsm.oevbackend.dto.request.LessonRequestDTO;
import com.unmsm.oevbackend.dto.response.LessonResponseDTO;

import java.util.List;

public interface ILessonService {

    LessonResponseDTO createLesson(Long courseId, LessonRequestDTO lessonRequestDTO);

    List<LessonResponseDTO> findLessonsByCourseId(Long courseId);

    void deleteLessonById(Long id);
}
