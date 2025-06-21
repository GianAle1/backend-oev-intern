package com.unmsm.oevbackend.service.impl;

import com.unmsm.oevbackend.dto.response.UserLessonProgressResponseDTO;
import com.unmsm.oevbackend.exception.AppException;
import com.unmsm.oevbackend.exception.UserNotFoundException;
import com.unmsm.oevbackend.mapper.UserLessonProgressMapper;
import com.unmsm.oevbackend.model.Lesson;
import com.unmsm.oevbackend.model.User;
import com.unmsm.oevbackend.model.UserLessonProgress;
import com.unmsm.oevbackend.model.enums.Status;
import com.unmsm.oevbackend.repository.ILessonRepository;
import com.unmsm.oevbackend.repository.IUserLessonProgressRepository;
import com.unmsm.oevbackend.repository.IUserRepository;
import com.unmsm.oevbackend.service.interfaces.IUserLessonProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserLessonProgressServiceImpl implements IUserLessonProgressService {

    private final IUserLessonProgressRepository userLessonProgressRepository;

    private final IUserRepository userRepository;

    private final ILessonRepository lessonRepository;

    private final UserLessonProgressMapper userLessonProgressMapper;

    @Override
    public List<UserLessonProgressResponseDTO> findUserLessonProgressesByUserIdAndCourseId(Long userId, Long courseId) {
        List<UserLessonProgress> userLessonProgressesByUserIdAndCourseId = userLessonProgressRepository.findUserLessonProgressesByUserIdAndCourseId(userId, courseId);
        return userLessonProgressMapper.entityToResponseDTO(userLessonProgressesByUserIdAndCourseId);
    }

    public void markLessonAsCompleted(Long userId, Long lessonId) {

        Optional<UserLessonProgress> userLessonProgressOptional = verifyUserLessonProgress(userId, lessonId);

        if (userLessonProgressOptional.isPresent()) {
            UserLessonProgress userLessonProgress = userLessonProgressOptional.get();
            userLessonProgress.setStatus(Status.COMPLETED);

            userLessonProgressRepository.save(userLessonProgress);
        }

    }


    @Override
    public void markLessonAsNotCompleted(Long userId, Long lessonId) {

        Optional<UserLessonProgress> userLessonProgressOptional = verifyUserLessonProgress(userId, lessonId);

        if (userLessonProgressOptional.isPresent()) {
            UserLessonProgress userLessonProgress = userLessonProgressOptional.get();
            userLessonProgress.setStatus(Status.NOT_COMPLETED);

            userLessonProgressRepository.save(userLessonProgress);
        }

    }

    private Optional<UserLessonProgress> verifyUserLessonProgress(Long userId, Long lessonId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado", HttpStatus.NOT_FOUND));

        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new AppException("Lección no encontrada", HttpStatus.NOT_FOUND));


        return userLessonProgressRepository.findByUserIdAndLessonId(user.getId(), lesson.getId());
    }


}
