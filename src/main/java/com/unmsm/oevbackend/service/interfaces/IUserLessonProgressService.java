package com.unmsm.oevbackend.service.interfaces;

import com.unmsm.oevbackend.dto.response.UserLessonProgressResponseDTO;
import com.unmsm.oevbackend.model.UserLessonProgress;

import java.util.List;

public interface IUserLessonProgressService {


    List<UserLessonProgressResponseDTO> findUserLessonProgressesByUserIdAndCourseId(Long userId, Long courseId);

    void markLessonAsCompleted(Long userId, Long lessonId);

    void markLessonAsNotCompleted(Long userId, Long lessonId);

}
