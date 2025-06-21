package com.unmsm.oevbackend.service.impl;

import com.unmsm.oevbackend.dto.request.LessonRequestDTO;
import com.unmsm.oevbackend.dto.response.LessonResponseDTO;
import com.unmsm.oevbackend.exception.AppException;
import com.unmsm.oevbackend.mapper.LessonMapper;
import com.unmsm.oevbackend.model.Course;
import com.unmsm.oevbackend.model.Lesson;
import com.unmsm.oevbackend.model.User;
import com.unmsm.oevbackend.model.UserLessonProgress;
import com.unmsm.oevbackend.model.enums.Status;
import com.unmsm.oevbackend.repository.ICourseRepository;
import com.unmsm.oevbackend.repository.IEnrollmentRepository;
import com.unmsm.oevbackend.repository.ILessonRepository;
import com.unmsm.oevbackend.repository.IUserLessonProgressRepository;
import com.unmsm.oevbackend.service.interfaces.ILessonService;
import com.unmsm.oevbackend.service.interfaces.IS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements ILessonService {

    private final ILessonRepository lessonRepository;

    private final ICourseRepository courseRepository;

    private final LessonMapper lessonMapper;

    private final IEnrollmentRepository enrollmentRepository;

    private final IUserLessonProgressRepository userLessonProgressRepository;

    private final IS3Service s3Service;

    @Override
    public LessonResponseDTO createLesson(Long courseId, LessonRequestDTO lessonRequestDTO) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isEmpty()) {
            throw new AppException("Course with id " + courseId + " not found", HttpStatus.NOT_FOUND);
        }

        Course courseEntity = course.get();

        Lesson lesson = lessonMapper.requestDTOToEntity(lessonRequestDTO);

        lesson.setCourse(courseEntity);
        lesson.setCreatedAt(LocalDateTime.now());

        lesson.setVideoKey(lessonRequestDTO.getVideoKey());


        Lesson lessonEntity = lessonRepository.save(lesson);

        assignLessonProgressToEnrolledUsers(courseEntity, lessonEntity);


        return lessonMapper.entityToDTO(lessonEntity);

    }

    private void assignLessonProgressToEnrolledUsers(Course course, Lesson lesson) {
        // Obtener los usuarios inscritos en el curso
        List<User> enrolledUsers = enrollmentRepository.findEnrolledUsersByCourseId(course.getId());

        // Crear el progreso para cada usuario si aún no existe
        List<UserLessonProgress> progressList = enrolledUsers.stream()
                .map(user -> UserLessonProgress.builder()
                        .user(user)
                        .lesson(lesson)
                        .status(Status.NOT_COMPLETED)
                        .build())
                .toList();

        userLessonProgressRepository.saveAll(progressList);
    }


    @Override
    public List<LessonResponseDTO> findLessonsByCourseId(Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isEmpty()) {
            throw new AppException("Course with id " + courseId + " not found", HttpStatus.NOT_FOUND);
        }

        List<Lesson> lessons = lessonRepository.findLessonsByCourseId(courseId);

        return lessonMapper.entityListToDTOList(lessons);

    }

    @Override
    public void deleteLessonById(Long id) {
        Optional<Lesson> lesson = lessonRepository.findById(id);
        if (lesson.isEmpty()) {
            throw new AppException("Lesson with id " + id + " not found", HttpStatus.NOT_FOUND);
        }

        boolean deleted = deleteLessonFromS3(lesson.get().getVideoKey());

        if (!deleted) {
            throw new AppException("Error deleting lesson video from S3", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        lessonRepository.deleteById(id);

    }

    public boolean deleteLessonFromS3(String videoKey) {
        try {
            s3Service.deleteFile("oev-mooc-bucket", videoKey);
            return true;
        } catch (S3Exception e) {
            return false;
        }
    }


    //TODO: update lesson
}
